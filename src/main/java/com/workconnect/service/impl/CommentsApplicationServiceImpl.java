package com.workconnect.service.impl;

import com.workconnect.dto.CommentsApplicationCreateUpdateDTO;
import com.workconnect.dto.CommentsApplicationDetailsDTO;
import com.workconnect.mapper.CommentsApplicationMapper;
import com.workconnect.model.entity.Application;
import com.workconnect.model.entity.CommentsApplication;
import com.workconnect.model.entity.User;
import com.workconnect.repository.ApplicationRepository;
import com.workconnect.repository.CommentsApplicationRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.CommentsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentsApplicationServiceImpl implements CommentsApplicationService {
    private final CommentsApplicationRepository commentsApplicationRepository;
    private final ApplicationRepository applicationRepository;
    private final CommentsApplicationMapper commentsApplicationMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommentsApplicationDetailsDTO> getAll(){
        List<CommentsApplication> commentsApplications= commentsApplicationRepository.findAll();
        return commentsApplications.stream().map(commentsApplicationMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentsApplicationDetailsDTO> paginate(Pageable pageable){
        Page<CommentsApplication> commentsApplications= commentsApplicationRepository.findAll(pageable);
        return commentsApplications.map(commentsApplicationMapper::toDetailsDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsApplicationDetailsDTO findById(Integer id){
        CommentsApplication commentsApplication = commentsApplicationRepository.findById(id).orElseThrow(()->new RuntimeException("CommentsApplication not founded with id: " + id));
        return commentsApplicationMapper.toDetailsDTO(commentsApplication);
    }

    @Transactional
    @Override
    public CommentsApplicationDetailsDTO create(CommentsApplicationCreateUpdateDTO commentsApplicationupdate){
        Application application = applicationRepository.findById(commentsApplicationupdate.getApplicationId())
                .orElseThrow(()-> new RuntimeException("Application not founded with id: " + commentsApplicationupdate.getApplicationId()));
        User user = userRepository.findById(commentsApplicationupdate.getUserId())
                .orElseThrow(()-> new RuntimeException("User not founded with id: " + commentsApplicationupdate.getUserId()));
        CommentsApplication commentsApplication = commentsApplicationMapper.toEntity(commentsApplicationupdate);
        commentsApplication.setCreated(LocalDateTime.now());
        commentsApplication.setApplication(application);
        commentsApplication.setUser(user);
        return commentsApplicationMapper.toDetailsDTO(commentsApplicationRepository.save(commentsApplication));
    }

    @Transactional
    @Override
    public CommentsApplicationDetailsDTO update(Integer id, CommentsApplicationCreateUpdateDTO updatedcommentsApplication){
        CommentsApplication commentsApplicationFromDb=commentsApplicationRepository.findById(id).orElseThrow(()->new RuntimeException("CommentsApplication not founded with id: " + id));
        Application application = applicationRepository.findById(updatedcommentsApplication.getApplicationId())
                        .orElseThrow(()-> new RuntimeException("Application not founded with id: " + updatedcommentsApplication.getApplicationId()));
        User user = userRepository.findById(updatedcommentsApplication.getUserId())
                        .orElseThrow(()-> new RuntimeException("User not founded with id: " + updatedcommentsApplication.getUserId()));
        commentsApplicationFromDb.setComment(updatedcommentsApplication.getComment());
        commentsApplicationFromDb.setCreated(LocalDateTime.now());
        commentsApplicationFromDb.setApplication(application);
        commentsApplicationFromDb.setUser(user);
        return commentsApplicationMapper.toDetailsDTO(commentsApplicationRepository.save(commentsApplicationFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id){
        CommentsApplication commentsApplication = commentsApplicationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("CommentsApplication not founded with id: " + id));
        commentsApplicationRepository.delete(commentsApplication);
    }
}
