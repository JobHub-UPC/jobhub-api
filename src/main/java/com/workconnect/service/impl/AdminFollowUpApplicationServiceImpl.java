package com.workconnect.service.impl;


import com.workconnect.model.entity.FollowUpApplication;
import com.workconnect.repository.FollowUpApplicationRepository;
import com.workconnect.service.AdminFollowUpApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminFollowUpApplicationServiceImpl implements AdminFollowUpApplicationService {
    private final FollowUpApplicationRepository followUpApplicationRepository;

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
                ()->new RuntimeException("Follow up Application not founded")
        );
    }
    @Transactional
    @Override
    public FollowUpApplication create(FollowUpApplication createdFollowUpApplication){
        return followUpApplicationRepository.save(createdFollowUpApplication);
    }

    @Transactional
    @Override
    public FollowUpApplication update(Integer id,FollowUpApplication updatedFollowUpApplication) {
        FollowUpApplication followUpApplicationFromDb=findById(id);
        followUpApplicationFromDb.setLastUpdate(updatedFollowUpApplication.getLastUpdate());
        return followUpApplicationRepository.save(followUpApplicationFromDb);
    }


    @Transactional
    @Override
    public void delete(Integer id){
        FollowUpApplication followUpApplication=findById(id);
        followUpApplicationRepository.delete(followUpApplication);
    }

}
