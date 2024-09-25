package com.workconnect.service.impl;

import com.workconnect.model.entity.ApplicantQualification;
import com.workconnect.model.entity.FollowUpApplication;
import com.workconnect.repository.ApplicantQualificationRepository;
import com.workconnect.repository.FollowUpApplicationRepository;
import com.workconnect.service.ApplicantQualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantQualificationServiceImpl implements ApplicantQualificationService {
    private final ApplicantQualificationRepository applicantQualificationRepository;
    private final FollowUpApplicationRepository followUpApplicationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicantQualification> getAll(){return applicantQualificationRepository.findAll();}

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicantQualification> paginate(Pageable pageable){return applicantQualificationRepository.findAll(pageable);}

    @Transactional(readOnly = true)
    @Override
    public ApplicantQualification findById(Integer id){
        return applicantQualificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Application Qualification not founded with id: " + id));
    }

    @Transactional
    @Override
    public ApplicantQualification create(ApplicantQualification applicantQualification){
        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(applicantQualification.getFollowUpApplication().getId())
                .orElseThrow(()-> new RuntimeException("Application not founded with id: " + applicantQualification.getFollowUpApplication().getId()));
        applicantQualification.setFollowUpApplication(followUpApplication);
        return applicantQualificationRepository.save(applicantQualification);
    }

    @Transactional
    @Override
    public ApplicantQualification update(Integer id,ApplicantQualification updateApplicantQualification){
        ApplicantQualification applicantQualificationFromDb=findById(id);

        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(updateApplicantQualification.getFollowUpApplication().getId())
                        .orElseThrow(()-> new RuntimeException("Application not founded with id: " + updateApplicantQualification.getFollowUpApplication().getId()));
        applicantQualificationFromDb.setLevel(updateApplicantQualification.getLevel());
        applicantQualificationFromDb.setFollowUpApplication(followUpApplication);
        return applicantQualificationRepository.save(applicantQualificationFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        ApplicantQualification applicantQualification= applicantQualificationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Applicant Qualification not founded with id: " + id));
        applicantQualificationRepository.delete(applicantQualification);
    }

}

