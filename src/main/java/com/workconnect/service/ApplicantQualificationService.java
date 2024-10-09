package com.workconnect.service;

import com.workconnect.dto.ApplicantQualificationCreateUpdateDTO;
import com.workconnect.dto.ApplicantQualificationDetailsDTO;
import com.workconnect.model.entity.ApplicantQualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantQualificationService {
    List<ApplicantQualificationDetailsDTO> getAll();
    Page<ApplicantQualificationDetailsDTO>paginate(Pageable pageable);
    ApplicantQualificationDetailsDTO findById(Integer id);
    ApplicantQualificationDetailsDTO create(ApplicantQualificationCreateUpdateDTO applicantQualification);
    ApplicantQualificationDetailsDTO update(Integer id,ApplicantQualificationCreateUpdateDTO applicantQualification);
    void delete(Integer id);
}
