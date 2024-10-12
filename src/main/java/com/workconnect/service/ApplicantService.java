package com.workconnect.service;

import com.workconnect.dto.ApplicantCreateDTO;
import com.workconnect.dto.ApplicantDTO;
import com.workconnect.dto.ApplicantDetailsDTO;
import com.workconnect.dto.ApplicantUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantService {
    List<ApplicantDetailsDTO> getAll();
    Page<ApplicantDetailsDTO> paginate(Pageable pageable);
    ApplicantDetailsDTO findById(Integer id);
    ApplicantDetailsDTO create(ApplicantCreateDTO applicantCreateDTO);
    ApplicantDetailsDTO update(Integer id, ApplicantUpdateDTO updateApplicantDTO);
    void delete(Integer id);
}
