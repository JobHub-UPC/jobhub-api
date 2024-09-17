package com.workconnect.service.impl;

import com.workconnect.model.entity.Applicant;
import com.workconnect.repository.ApplicantRepository;
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
                ()-> new RuntimeException("Applicant Not founded")
        );
    }

    @Transactional
    @Override
    public Applicant create(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Transactional
    @Override
    public Applicant update(Integer id, Applicant updateApplicant) {
        Applicant applicantFromDb = findById(id);
        applicantFromDb.setFirstName(updateApplicant.getFirstName());
        applicantFromDb.setLastName(updateApplicant.getLastName());
        applicantFromDb.setDescription(updateApplicant.getDescription());
        applicantFromDb.setEmail(updateApplicant.getEmail());
        applicantFromDb.setPhone(updateApplicant.getPhone());
        applicantFromDb.setCollege(updateApplicant.getCollege());
        applicantFromDb.setDegree(updateApplicant.getDegree());
        applicantFromDb.setCountry(updateApplicant.getCountry());
        return applicantRepository.save(applicantFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Applicant applicant = findById(id);
        applicantRepository.delete(applicant);
    }
}
