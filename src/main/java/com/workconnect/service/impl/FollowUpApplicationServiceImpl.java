package com.workconnect.service.impl;


import com.workconnect.dto.FollowUpApplicationCreateUpdateDTO;
import com.workconnect.dto.FollowUpApplicationDetailsDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.FollowUpApplicantionMapper;
import com.workconnect.model.entity.Application;
import com.workconnect.model.entity.FollowUpApplication;
import com.workconnect.model.entity.JobPhase;
import com.workconnect.repository.ApplicationRepository;
import com.workconnect.repository.FollowUpApplicationRepository;
import com.workconnect.repository.JobPhaseRepository;
import com.workconnect.service.FollowUpApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowUpApplicationServiceImpl implements FollowUpApplicationService {
    private final FollowUpApplicationRepository followUpApplicationRepository;
    private final JobPhaseRepository jobPhaseRepository;
    private final ApplicationRepository applicationRepository;
    private final FollowUpApplicantionMapper followUpApplicantionMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FollowUpApplicationDetailsDTO> getAll() {
        List<FollowUpApplication> followUpApplications = followUpApplicationRepository.findAll();
        return followUpApplications.stream()
                .map(followUpApplicantionMapper::toDetailsDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<FollowUpApplicationDetailsDTO> paginate(Pageable pageable) {
        Page<FollowUpApplication> followUpApplications = followUpApplicationRepository.findAll(pageable);
        return followUpApplications.map(followUpApplicantionMapper::toDetailsDTO);
    }
    @Transactional(readOnly = true)
    @Override
    public FollowUpApplicationDetailsDTO findById(Integer id) {
        FollowUpApplication followUpApplication =  followUpApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Follow up Application not found with id: " + id));
        return followUpApplicantionMapper.toDetailsDTO(followUpApplication);
    }
    @Transactional
    @Override
    public FollowUpApplicationDetailsDTO create(FollowUpApplicationCreateUpdateDTO followUpApplicationCreateUpdateDTO){
        JobPhase jobPhase = jobPhaseRepository.findById(followUpApplicationCreateUpdateDTO.getJobPhaseId()).orElseThrow(()-> new RuntimeException("Job phase not founded with id: " + followUpApplicationCreateUpdateDTO.getJobPhaseId()));
        Application application = applicationRepository.findById(followUpApplicationCreateUpdateDTO.getApplicantId()).orElseThrow(()-> new RuntimeException("Application not founded with id: " + followUpApplicationCreateUpdateDTO.getApplicantId()));

        FollowUpApplication followUpApplication=followUpApplicantionMapper.toEntity(followUpApplicationCreateUpdateDTO);
        followUpApplication.setApplication(application);
        followUpApplication.setJobphase(jobPhase);
        followUpApplication.setStatus(followUpApplicationCreateUpdateDTO.getStatus());
        return followUpApplicantionMapper.toDetailsDTO(followUpApplicationRepository.save(followUpApplication));
    }

    @Transactional
    @Override
    public FollowUpApplicationDetailsDTO update(Integer id,FollowUpApplicationCreateUpdateDTO followUpApplicationCreateUpdateDTO) {

        FollowUpApplication followUpApplicationfromDb = followUpApplicationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Follow up Application not found with id: " + id));


        JobPhase jobPhase = jobPhaseRepository.findById(followUpApplicationCreateUpdateDTO.getJobPhaseId())
                .orElseThrow(() -> new RuntimeException("Follow up Application not found with id: " + followUpApplicationCreateUpdateDTO.getJobPhaseId()));
        Application application1 = applicationRepository.findById(followUpApplicationCreateUpdateDTO.getApplicantId())
                .orElseThrow(() -> new RuntimeException("Follow up Application not found with id: " + followUpApplicationCreateUpdateDTO.getApplicantId()));

;

        followUpApplicationfromDb.setApplication(application1);
        followUpApplicationfromDb.setLastUpdate(LocalDateTime.now());
        followUpApplicationfromDb.setApplicationDate(application1.getDateCreated());
        followUpApplicationfromDb.setStatus(followUpApplicationCreateUpdateDTO.getStatus());
        followUpApplicationfromDb.setJobphase(jobPhase);

        return followUpApplicantionMapper.toDetailsDTO(followUpApplicationRepository.save(followUpApplicationfromDb));
    }


    @Transactional
    @Override
    public void delete(Integer id){
        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Follow up Application not founded with id: " + id));
        followUpApplicationRepository.delete(followUpApplication);
    }

}
