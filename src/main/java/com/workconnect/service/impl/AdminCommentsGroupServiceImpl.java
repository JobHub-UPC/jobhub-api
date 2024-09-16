package com.workconnect.service.impl;

import com.workconnect.model.entity.CommentsGroup;
import com.workconnect.repository.CommentsGroupRepository;
import com.workconnect.service.AdminCommentsGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommentsGroupServiceImpl implements AdminCommentsGroupService {
    private final CommentsGroupRepository commentsGroupRepository;
    @Transactional(readOnly = true)
    @Override
    public List<CommentsGroup> getAll(){
        return commentsGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentsGroup> paginate(Pageable pageable){
        return commentsGroupRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsGroup findById(Integer id){
        return commentsGroupRepository.findById(id).orElseThrow(
                ()->new RuntimeException("CommentsGroup Not Founded")
        );
    }

    @Transactional
    @Override
    public CommentsGroup create(CommentsGroup commentsGroup){
        return commentsGroupRepository.save(commentsGroup);
    }

    @Transactional
    @Override
    public CommentsGroup update(Integer id,CommentsGroup commentsGroup){
        CommentsGroup commentsGroupFromDb=findById(id);
        commentsGroupFromDb.setContent(commentsGroup.getContent());
        commentsGroupFromDb.setLikesCount(commentsGroup.getLikesCount());
        commentsGroupFromDb.setPostedDate(commentsGroup.getPostedDate());
        return commentsGroupRepository.save(commentsGroupFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        CommentsGroup commentsGroup=findById(id);
        commentsGroupRepository.delete(commentsGroup);
    }
}
