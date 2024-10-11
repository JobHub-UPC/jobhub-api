package com.workconnect.service;

import com.workconnect.dto.JobCreateUpdateDTO;
import com.workconnect.dto.JobDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminJobService {
    List<JobDetailsDTO> getAll();
    Page<JobDetailsDTO> paginate(Pageable pageable);
    JobDetailsDTO findById(Integer id);
    JobDetailsDTO create(JobCreateUpdateDTO jobCreateUpdateDTO);
    JobDetailsDTO update(Integer id, JobCreateUpdateDTO updateJobDTO);
    void delete(Integer id);

}
