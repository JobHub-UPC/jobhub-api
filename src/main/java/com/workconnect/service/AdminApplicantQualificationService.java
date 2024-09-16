package com.workconnect.service;

import com.workconnect.model.entity.ApplicantQualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminApplicantQualificationService {
    List<ApplicantQualification> getAll();
    Page<ApplicantQualification>paginate(Pageable pageable);
    ApplicantQualification findById(Integer id);
    ApplicantQualification create(ApplicantQualification applicantQualification);
    ApplicantQualification update(Integer id,ApplicantQualification applicantQualification);
    void delete(Integer id);
}
