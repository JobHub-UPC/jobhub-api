package com.workconnect.service.impl;

import com.workconnect.model.entity.CommentsApplication;
import com.workconnect.repository.CommentsApplicationRepository;
import com.workconnect.service.AdminCommentsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCommentsApplicationServiceImpl implements AdminCommentsApplicationService {
    private final CommentsApplicationRepository commentsApplicationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommentsApplication> getAll(){
        return commentsApplicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentsApplication> paginate(Pageable pageable){
        return commentsApplicationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsApplication findById(Integer id){
        return commentsApplicationRepository.findById(id).orElseThrow(()->new RuntimeException("CommentsApplication not founded"));
    }

    @Transactional
    @Override
    public CommentsApplication create(CommentsApplication commentsApplication){
        return commentsApplicationRepository.save(commentsApplication);
    }

    @Transactional
    @Override
    public CommentsApplication update(Integer id, CommentsApplication updatedcommentsApplication){
        CommentsApplication commentsApplicationFromDb=findById(id);
        commentsApplicationFromDb.setComment(updatedcommentsApplication.getComment());
        commentsApplicationFromDb.setCreated(updatedcommentsApplication.getCreated());
        return commentsApplicationRepository.save(commentsApplicationFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        CommentsApplication commentsApplication=findById(id);
        commentsApplicationRepository.delete(commentsApplication);
    }
}
