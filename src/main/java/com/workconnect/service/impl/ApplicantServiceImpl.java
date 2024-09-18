package com.workconnect.service.impl;

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

    @Transactional(readOnly = true)
    @Override
    public List<Applicant> getAll() {
        return applicantRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Applicant> paginate(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Applicant findById(Integer id) {
        return applicantRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Applicant not found with id: " + id)
        );
    }

    @Transactional
    @Override
    public Applicant create(Applicant applicant) {
        //Asigna el user antes de guardar
        User user = userRepository.findById(applicant.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id:" + applicant.getUser().getId()));
        applicant.setUser(user);
        return applicantRepository.save(applicant);
    }

    @Transactional
    @Override
    public Applicant update(Integer id, Applicant updateApplicant) {
        Applicant applicantFromDb = findById(id); // Utiliza orElseThrow dentro de findById

        //Asigna el user antes de actualizar
        User user = userRepository.findById(updateApplicant.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + updateApplicant.getUser().getId()));

        applicantFromDb.setFirstName(updateApplicant.getFirstName());
        applicantFromDb.setLastName(updateApplicant.getLastName());
        applicantFromDb.setDescription(updateApplicant.getDescription());
        applicantFromDb.setEmail(updateApplicant.getEmail());
        applicantFromDb.setPhone(updateApplicant.getPhone());
        applicantFromDb.setCollege(updateApplicant.getCollege());
        applicantFromDb.setDegree(updateApplicant.getDegree());
        applicantFromDb.setCountry(updateApplicant.getCountry());
        applicantFromDb.setUser(user);
        return applicantRepository.save(applicantFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with id: " + id));
        applicantRepository.delete(applicant);
    }
}
