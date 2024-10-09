package com.workconnect.service.impl;

import com.workconnect.dto.ApplicantQualificationCreateUpdateDTO;
import com.workconnect.dto.ApplicantQualificationDetailsDTO;
import com.workconnect.mapper.ApplicantQualificationMapper;
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
    private final ApplicantQualificationMapper applicantQualificationMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicantQualificationDetailsDTO> getAll(){
        List<ApplicantQualification> applicantQualifications= applicantQualificationRepository.findAll();
        return applicantQualifications.stream().map(applicantQualificationMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicantQualificationDetailsDTO> paginate(Pageable pageable){
        Page<ApplicantQualification> applicantQualification= applicantQualificationRepository.findAll(pageable);
        return applicantQualification.map(applicantQualificationMapper::toDetailsDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicantQualificationDetailsDTO findById(Integer id){
        ApplicantQualification applicantQualification= applicantQualificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Application Qualification not founded with id: " + id));
        return applicantQualificationMapper.toDetailsDTO(applicantQualification);
    }

    @Transactional
    @Override
    public ApplicantQualificationDetailsDTO create(ApplicantQualificationCreateUpdateDTO applicantQualification){
        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(applicantQualification.getFollowUpApplication())
                .orElseThrow(()-> new RuntimeException("Application not founded with id: " + applicantQualification.getFollowUpApplication()));
        ApplicantQualification applicantQualification1=applicantQualificationMapper.toEntity(applicantQualification);
        applicantQualification1.setFollowUpApplication(followUpApplication);
        return applicantQualificationMapper.toDetailsDTO(applicantQualificationRepository.save(applicantQualification1));
    }

    @Transactional
    @Override
    public ApplicantQualificationDetailsDTO update(Integer id,ApplicantQualificationCreateUpdateDTO updateApplicantQualification){
        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(updateApplicantQualification.getFollowUpApplication())
                        .orElseThrow(()-> new RuntimeException("Application not founded with id: " + updateApplicantQualification.getFollowUpApplication()));
        ApplicantQualification applicantQualificationFromDb = applicantQualificationRepository.findById(id).orElseThrow(()-> new RuntimeException("Applicant Qualification not founded with id: " + id));
        applicantQualificationFromDb.setLevel(updateApplicantQualification.getQualification())  ;
        applicantQualificationFromDb.setFollowUpApplication(followUpApplication);
        return applicantQualificationMapper.toDetailsDTO(applicantQualificationRepository.save(applicantQualificationFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id){
        ApplicantQualification applicantQualification= applicantQualificationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Applicant Qualification not founded with id: " + id));
        applicantQualificationRepository.delete(applicantQualification);
    }

}

