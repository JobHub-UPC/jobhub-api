package com.workconnect.service;

import com.workconnect.dto.ApplicantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantService {
    List<ApplicantDTO> getAll();
    Page<ApplicantDTO> paginate(Pageable pageable);
    ApplicantDTO findById(Integer id);
    ApplicantDTO create(ApplicantDTO applicantDTO);
    ApplicantDTO update(Integer id, ApplicantDTO updateApplicantDTO);
    void delete(Integer id);
}
