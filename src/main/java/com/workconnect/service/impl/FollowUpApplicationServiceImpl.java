package com.workconnect.service.impl;


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

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowUpApplicationServiceImpl implements FollowUpApplicationService {
    private final FollowUpApplicationRepository followUpApplicationRepository;
    private final JobPhaseRepository jobPhaseRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FollowUpApplication> getAll(){
        return followUpApplicationRepository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<FollowUpApplication> paginate(Pageable pageable){
        return followUpApplicationRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    @Override
    public FollowUpApplication findById(Integer id){
        return followUpApplicationRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Follow up Application not founded with id: " + id)
        );
    }
    @Transactional
    @Override
    public FollowUpApplication create(FollowUpApplication createdFollowUpApplication){
        JobPhase jobPhase = jobPhaseRepository.findById(createdFollowUpApplication.getJobphase().getId()).orElseThrow(()-> new RuntimeException("Job phase not founded with id: " + createdFollowUpApplication.getJobphase().getId()));
        Application application = applicationRepository.findById(createdFollowUpApplication.getApplication().getId()).orElseThrow(()-> new RuntimeException("Application not founded with id: " + createdFollowUpApplication.getApplication().getId()));

        createdFollowUpApplication.setApplication(application);
        createdFollowUpApplication.setJobphase(jobPhase);
        return followUpApplicationRepository.save(createdFollowUpApplication);
    }

    @Transactional
    @Override
    public FollowUpApplication update(Integer id,FollowUpApplication updatedFollowUpApplication) {
        FollowUpApplication followUpApplicationFromDb=findById(id);

        JobPhase jobphase = jobPhaseRepository.findById(updatedFollowUpApplication.getJobphase().getId()).orElseThrow(()-> new RuntimeException("Jobphase not founded with id: " + updatedFollowUpApplication.getJobphase().getId()));
        Application application = applicationRepository.findById(updatedFollowUpApplication.getApplication().getId()).orElseThrow(()-> new RuntimeException("Application not founded with id: " + updatedFollowUpApplication.getApplication().getId()));

        followUpApplicationFromDb.setLastUpdate(updatedFollowUpApplication.getLastUpdate());
        followUpApplicationFromDb.setApplication(application);
        followUpApplicationFromDb.setJobphase(jobphase);
        return followUpApplicationRepository.save(followUpApplicationFromDb);
    }


    @Transactional
    @Override
    public void delete(Integer id){
        FollowUpApplication followUpApplication = followUpApplicationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Follow up Application not founded with id: " + id));
        followUpApplicationRepository.delete(followUpApplication);
    }

}
