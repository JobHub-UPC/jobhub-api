package com.workconnect.service;

import com.workconnect.model.entity.CommentsGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentsGroupService {
    List<CommentsGroup> getAll();
    Page<CommentsGroup> paginate(Pageable pageable);
    CommentsGroup findById(Integer id);
    CommentsGroup create(CommentsGroup commentsGroup);
    CommentsGroup update(Integer id, CommentsGroup commentsGroup);
    void delete(Integer id);
}
