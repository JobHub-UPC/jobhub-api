package com.workconnect.service;


import com.workconnect.model.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCommunityService {
    List<Community> getAll();
    Page<Community> paginate(Pageable pageable);
    Community findById(Integer id);
    Community create(Community community);
    Community update(Integer id, Community community);
    void delete(Integer id);

}
