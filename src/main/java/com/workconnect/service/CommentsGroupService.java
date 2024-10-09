package com.workconnect.service;

import com.workconnect.dto.CommentsGroupCreateUpdateDTO;
import com.workconnect.dto.CommentsGroupDetailsDTO;
import com.workconnect.model.entity.CommentsGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentsGroupService {
    List<CommentsGroupDetailsDTO> getAll();
    Page<CommentsGroupDetailsDTO> paginate(Pageable pageable);
    CommentsGroupDetailsDTO findById(Integer id);
    CommentsGroupDetailsDTO create(CommentsGroupCreateUpdateDTO commentsGroup);
    CommentsGroupDetailsDTO update(Integer id, CommentsGroupCreateUpdateDTO commentsGroup);
    void delete(Integer id);
}
