package com.workconnect.service.impl;

import com.workconnect.model.entity.ApplicantQualification;
import com.workconnect.repository.ApplicantQualificationRepository;
import com.workconnect.service.AdminApplicantQualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminApplicantQualificationServiceImpl implements AdminApplicantQualificationService {
    private final ApplicantQualificationRepository applicantQualificationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicantQualification> getAll(){return applicantQualificationRepository.findAll();}

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicantQualification> paginate(Pageable pageable){return applicantQualificationRepository.findAll(pageable);}

    @Transactional(readOnly = true)
    @Override
    public ApplicantQualification findById(Integer id){
        return applicantQualificationRepository.findById(id).orElseThrow(()->new RuntimeException("User not founded"));
    }

    @Transactional
    @Override
    public ApplicantQualification create(ApplicantQualification applicantQualification){
        return applicantQualificationRepository.save(applicantQualification);
    }

    @Transactional
    @Override
    public ApplicantQualification update(Integer id,ApplicantQualification updateApplicantQualification){
        ApplicantQualification applicantQualificationFromDb=findById(id);
        applicantQualificationFromDb.setLevel(updateApplicantQualification.getLevel());
        return applicantQualificationRepository.save(applicantQualificationFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        ApplicantQualification applicantQualification=findById(id);
        applicantQualificationRepository.delete(applicantQualification);
    }

}

