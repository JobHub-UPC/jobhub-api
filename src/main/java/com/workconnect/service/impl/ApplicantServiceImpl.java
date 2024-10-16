package com.workconnect.service.impl;

import com.workconnect.dto.ApplicantCreateDTO;
import com.workconnect.dto.ApplicantDetailsDTO;
import com.workconnect.dto.ApplicantUpdateDTO;
import com.workconnect.exception.BadRequestException;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.ApplicantMapper;
import com.workconnect.model.entity.*;
import com.workconnect.model.enums.ERole;
import com.workconnect.repository.*;
import com.workconnect.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final ApplicantMapper applicantMapper;
    private final ApplicationRepository applicationRepository;
    private final FollowUpApplicationRepository followUpApplicationRepository;
    private final ApplicantQualificationRepository applicantQualificationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicantDetailsDTO> getAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream()
                .map(applicantMapper::toDetailsDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicantDetailsDTO> paginate(Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        return applicants.map(applicantMapper::toDetailsDto);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicantDetailsDTO findById(Integer id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + id));
        return applicantMapper.toDetailsDto(applicant);
    }

    @Transactional
    @Override
    public ApplicantDetailsDTO create(ApplicantCreateDTO applicantCreateDTO) {
        applicantRepository.findApplicantByEmail(applicantCreateDTO.getEmail())
                .ifPresent(existingApplicant -> {
                    throw new BadRequestException("Applicant already exists");
                });

        //Crear un nuevo usuario
        User user = new User();
        user.setEmail(applicantCreateDTO.getEmail());
        user.setPassword(applicantCreateDTO.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        //user.setRole("Applicant");

        Role applicantRole = new Role();
        applicantRole.setName(ERole.Applicant);
        user.setRole(applicantRole);


        user = userRepository.save(user);


        Applicant applicant = applicantMapper.toEntity(applicantCreateDTO);
        applicant.setUser(user);
        applicant = applicantRepository.save(applicant);
        return applicantMapper.toDetailsDto(applicant);
    }

    @Transactional
    @Override
    public ApplicantDetailsDTO update(Integer id, ApplicantUpdateDTO updateApplicantDTO) {
        Applicant applicantFromDb = applicantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + id));

        applicantRepository.findApplicantByEmail(updateApplicantDTO.getEmail())
                        .filter(applicant -> !applicant.getEmail().equals(updateApplicantDTO.getEmail()))
                                .ifPresent(existingApplicant -> {
                                    throw new BadRequestException("Applicant already exists");
                                });

        applicantFromDb.setFirstName(updateApplicantDTO.getFirstName());
        applicantFromDb.setLastName(updateApplicantDTO.getLastName());
        applicantFromDb.setDescription(updateApplicantDTO.getDescription());
        applicantFromDb.setEmail(updateApplicantDTO.getEmail());
        applicantFromDb.setPhone(updateApplicantDTO.getPhone());
        applicantFromDb.setCollege(updateApplicantDTO.getCollege());
        applicantFromDb.setDegree(updateApplicantDTO.getDegree());
        applicantFromDb.setCountry(updateApplicantDTO.getCountry());

        User user = applicantFromDb.getUser();
        if (user != null){
            user.setEmail(applicantFromDb.getEmail());
            userRepository.save(user);
        }

        applicantFromDb = applicantRepository.save(applicantFromDb);
        return applicantMapper.toDetailsDto(applicantFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        // Encuentra el aplicante
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + id));

        // Encuentra las aplicaciones relacionadas al aplicante
        List<Application> applications = applicationRepository.findByApplicantId(id);

        // Para cada aplicación, elimina sus calificaciones que están referenciando a follow up applications
        for (Application application : applications) {
            List<FollowUpApplication> followUpApplications = followUpApplicationRepository.findByApplicationId(application.getId());
            for (FollowUpApplication followUpApplication : followUpApplications) {
                // Elimina las calificaciones del aplicante que están referenciando el follow up application
                List<ApplicantQualification> qualifications = applicantQualificationRepository.findByFollowUpApplicationId(followUpApplication.getId());
                for (ApplicantQualification qualification : qualifications) {
                    applicantQualificationRepository.delete(qualification);
                }

                // Luego elimina el follow up application
                followUpApplicationRepository.delete(followUpApplication);
            }
        }

        // Ahora elimina las aplicaciones
        applicationRepository.deleteByApplicantId(id);

        // Finalmente, elimina el aplicante
        applicantRepository.delete(applicant);
    }



}
