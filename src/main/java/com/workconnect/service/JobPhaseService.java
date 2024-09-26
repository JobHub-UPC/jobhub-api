package com.workconnect.service;

import com.workconnect.dto.JobPhaseCreateUpdateDTO;
import com.workconnect.dto.JobPhaseDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobPhaseService {
    List<JobPhaseDetailsDTO> getAll();
    Page<JobPhaseDetailsDTO> paginate(Pageable pageable);
    JobPhaseDetailsDTO findById(Integer id);
    JobPhaseDetailsDTO create(JobPhaseCreateUpdateDTO jobPhase);
    JobPhaseDetailsDTO update(Integer id,JobPhaseCreateUpdateDTO jobPhase);
    void delete(Integer id);
}
