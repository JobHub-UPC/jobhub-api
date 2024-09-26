package com.workconnect.service.impl;


import com.workconnect.dto.JobPhaseCreateUpdateDTO;
import com.workconnect.dto.JobPhaseDetailsDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.JobPhaseMapper;
import com.workconnect.model.entity.Application;
import com.workconnect.model.entity.FollowUpApplication;
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

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobPhaseServiceImpl implements JobPhaseService {
    private final JobPhaseRepository jobPhaseRepository;
    private final JobRepository jobRepository;
    private final JobPhaseMapper jobPhaseMapper;

    @Transactional(readOnly = true)
    @Override
    public List<JobPhaseDetailsDTO> getAll(){
        List<JobPhase> jobPhases = jobPhaseRepository.findAll();
        return jobPhases.stream()
                .map(jobPhaseMapper::toDetailsDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobPhaseDetailsDTO> paginate(Pageable pageable){
        Page<JobPhase> jobPhases = jobPhaseRepository.findAll(pageable);
        return jobPhases.map(jobPhaseMapper::toDetailsDTO);

    }

    @Transactional(readOnly = true)
    @Override
    public JobPhaseDetailsDTO findById(Integer id){
        JobPhase jobPhase =  jobPhaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobPhase not found with id: " + id));
        return jobPhaseMapper.toDetailsDTO(jobPhase);

    }

    @Transactional
    @Override
    public JobPhaseDetailsDTO create(JobPhaseCreateUpdateDTO jobPhaseCreateUpdateDTO){
        Job job = jobRepository.findById(jobPhaseCreateUpdateDTO.getJobId()).orElseThrow(()-> new RuntimeException("Job not founded with id: " + jobPhaseCreateUpdateDTO.getJobId()));

        JobPhase jobPhase=jobPhaseMapper.toEntity(jobPhaseCreateUpdateDTO);
        jobPhase.setJob(job);
        jobPhase.setName(jobPhaseCreateUpdateDTO.getName());
        return jobPhaseMapper.toDetailsDTO(jobPhaseRepository.save(jobPhase));

    }
    @Transactional
    @Override
    public JobPhaseDetailsDTO update(Integer id,JobPhaseCreateUpdateDTO jobPhaseCreateUpdateDTO){

        JobPhase jobPhasefromDb = jobPhaseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("JobPhase not found with id: " + id));


        Job job = jobRepository.findById(jobPhaseCreateUpdateDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobPhaseCreateUpdateDTO.getJobId()));

        jobPhasefromDb.setJob(job);
        jobPhasefromDb.setName(jobPhaseCreateUpdateDTO.getName());

        return jobPhaseMapper.toDetailsDTO(jobPhaseRepository.save(jobPhasefromDb));

    }

    @Transactional
    @Override
    public void delete(Integer id){
        JobPhase jobPhase = jobPhaseRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("JobPhase Not Founded with id: " + id));
        jobPhaseRepository.delete(jobPhase);
    }
}
