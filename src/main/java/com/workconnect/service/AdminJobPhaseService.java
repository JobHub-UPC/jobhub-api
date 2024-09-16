package com.workconnect.service;

import com.workconnect.model.entity.JobPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminJobPhaseService {
    List<JobPhase> getAll();
    Page<JobPhase> paginate(Pageable pageable);
    JobPhase findById(Integer id);
    JobPhase create(JobPhase jobPhase);
    JobPhase update(Integer id,JobPhase jobPhase);
    void delete(Integer id);
}
