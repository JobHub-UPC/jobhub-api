package com.workconnect.service;

import com.workconnect.dto.CommentsApplicationCreateUpdateDTO;
import com.workconnect.dto.CommentsApplicationDetailsDTO;
import com.workconnect.model.entity.CommentsApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentsApplicationService {
    List<CommentsApplicationDetailsDTO> getAll();
    Page<CommentsApplicationDetailsDTO> paginate(Pageable pageable);
    CommentsApplicationDetailsDTO findById(Integer id);
    CommentsApplicationDetailsDTO create(CommentsApplicationCreateUpdateDTO commentsApplication);
    CommentsApplicationDetailsDTO update(Integer id, CommentsApplicationCreateUpdateDTO commentsApplication);
    void delete(Integer id);
}
