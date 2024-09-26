package com.workconnect.service.impl;

import com.workconnect.dto.ApplicantDTO;
import com.workconnect.exception.BadRequestException;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.ApplicantMapper;
import com.workconnect.model.entity.Applicant;
import com.workconnect.model.entity.User;
import com.workconnect.repository.ApplicantRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;
    private final ApplicantMapper applicantMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicantDTO> getAll() {
        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream()
                .map(applicantMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicantDTO> paginate(Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        return applicants.map(applicantMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicantDTO findById(Integer id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + id));
        return applicantMapper.toDTO(applicant);
    }

    @Transactional
    @Override
    public ApplicantDTO create(ApplicantDTO applicantDTO) {
        applicantRepository.findApplicantByEmail(applicantDTO.getEmail())
                .ifPresent(existingApplicant -> {
                    throw new BadRequestException("Applicant already exists");
                });

        //Asigna el user antes de guardar
        User user = userRepository.findById(applicantDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + applicantDTO.getId()));

        Applicant applicant = applicantMapper.toEntity(applicantDTO);
        applicant.setUser(user);
        applicant = applicantRepository.save(applicant);
        return applicantMapper.toDTO(applicant);
    }

    @Transactional
    @Override
    public ApplicantDTO update(Integer id, ApplicantDTO updateApplicantDTO) {
        Applicant applicantFromDb = applicantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + id));

        //Asigna el user antes de actualizar
        User user = userRepository.findByEmail(updateApplicantDTO.getEmail())
                        .orElseThrow(() -> new ResourceNotFoundException("applicant not found"));

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

        applicantFromDb = applicantRepository.save(applicantFromDb);
        return applicantMapper.toDTO(applicantFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + id));
        applicantRepository.delete(applicant);
    }
}
