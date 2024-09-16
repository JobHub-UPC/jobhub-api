package com.workconnect.service.impl;


import com.workconnect.model.entity.JobPhase;
import com.workconnect.repository.JobPhaseRepository;
import com.workconnect.service.AdminJobPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminJobPhaseServiceImpl implements AdminJobPhaseService {
    private final JobPhaseRepository jobPhaseRepository;
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
                ()->new RuntimeException("JobPhase Not Founded")
        );
    }

    @Transactional
    @Override
    public JobPhase create(JobPhase jobPhase){
        return jobPhaseRepository.save(jobPhase);
    }
    @Transactional
    @Override
    public JobPhase update(Integer id,JobPhase updateJobPhase){
        JobPhase jobPhaseFromDb=findById(id);
        jobPhaseFromDb.setName(updateJobPhase.getName());
        return jobPhaseRepository.save(jobPhaseFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        JobPhase jobPhase=findById(id);
        jobPhaseRepository.delete(jobPhase);
    }
}
