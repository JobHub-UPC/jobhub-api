package com.workconnect.service;

import com.workconnect.model.entity.FollowUpApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminFollowUpApplicationService {
    List<FollowUpApplication> getAll();
    Page<FollowUpApplication> paginate(Pageable pageable);
    FollowUpApplication findById(Integer id);
    FollowUpApplication create(FollowUpApplication followUpApplication);
    FollowUpApplication update(Integer id,FollowUpApplication followUpApplication);
    void delete(Integer id);
}
