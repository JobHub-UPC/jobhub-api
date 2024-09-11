package com.workconnect.service.impl;

import com.workconnect.model.entity.Job;
import com.workconnect.repository.JobRepository;
import com.workconnect.service.AdminJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminJobServiceImpl implements AdminJobService {
    private final JobRepository jobRepository;

    @Transactional(readOnly = true)
    @Override
    public  List<Job> getAll(){
        return jobRepository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Job> paginate(Pageable pageable){
        return jobRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public Job findById(Integer id){
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
    @Transactional
    @Override
    public Job create(Job job){
        job.setPostedDate(LocalDateTime.now());
        return jobRepository.save(job);
    }
    @Transactional
    @Override
    public Job update(Integer id, Job job){
        Job jobFromDb = findById(id);
        jobFromDb.setJobType(job.getJobType());
        jobFromDb.setCompany(job.getCompany());
        jobFromDb.setPostedDate(job.getPostedDate());
        return jobRepository.save(jobFromDb);
    }
    @Override
    public void delete(Integer id) {
        Job job = findById(id);
        jobRepository.delete(job);
    }
}
