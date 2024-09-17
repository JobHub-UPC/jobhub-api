package com.workconnect.service;

import com.workconnect.model.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminJobService {
    List<Job> getAll();
    Page<Job> paginate(Pageable pageable);
    Job findById(Integer id);
    Job create(Job job);
    Job update(Integer id, Job updateJob);
    void delete(Integer id);

}
