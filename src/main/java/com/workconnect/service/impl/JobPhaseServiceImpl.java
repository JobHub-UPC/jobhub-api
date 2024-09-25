package com.workconnect.service.impl;


import com.workconnect.model.entity.Job;
import com.workconnect.model.entity.JobPhase;
import com.workconnect.repository.JobPhaseRepository;
import com.workconnect.repository.JobRepository;
import com.workconnect.service.JobPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobPhaseServiceImpl implements JobPhaseService {
    private final JobPhaseRepository jobPhaseRepository;
    private final JobRepository jobRepository;

    @Transactional(readOnly = true)
    @Override
    public List<JobPhase> getAll(){
        return jobPhaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobPhase> paginate(Pageable pageable){
        return jobPhaseRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public JobPhase findById(Integer id){
        return jobPhaseRepository.findById(id).orElseThrow(
                ()->new RuntimeException("JobPhase Not Founded with id: " + id)
        );
    }

    @Transactional
    @Override
    public JobPhase create(JobPhase jobPhase){
        Job job = jobRepository.findById(jobPhase.getJob().getId()).orElseThrow(()-> new RuntimeException("Job not found with id: " + jobPhase.getJob().getId()));
        jobPhase.setJob(job);
        return jobPhaseRepository.save(jobPhase);
    }
    @Transactional
    @Override
    public JobPhase update(Integer id,JobPhase updateJobPhase){
        JobPhase jobPhaseFromDb=findById(id);

        Job job = jobRepository.findById(updateJobPhase.getJob().getId()).orElseThrow(()-> new RuntimeException("Job not found with id: " + updateJobPhase.getJob().getId()));

        jobPhaseFromDb.setName(updateJobPhase.getName());
        jobPhaseFromDb.setJob(job);
        return jobPhaseRepository.save(jobPhaseFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        JobPhase jobPhase = jobPhaseRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("JobPhase Not Founded with id: " + id));
        jobPhaseRepository.delete(jobPhase);
    }
}
