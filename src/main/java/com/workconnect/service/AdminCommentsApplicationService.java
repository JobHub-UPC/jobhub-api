package com.workconnect.service;

import com.workconnect.model.entity.CommentsApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCommentsApplicationService {
    List<CommentsApplication> getAll();
    Page<CommentsApplication> paginate(Pageable pageable);
    CommentsApplication findById(Integer id);
    CommentsApplication create(CommentsApplication commentsApplication);
    CommentsApplication update(Integer id,CommentsApplication commentsApplication);
    void delete(Integer id);
}
