package com.workconnect.service;

import com.workconnect.dto.FollowUpApplicationCreateUpdateDTO;
import com.workconnect.dto.FollowUpApplicationDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowUpApplicationService {
    List<FollowUpApplicationDetailsDTO> getAll();
    Page<FollowUpApplicationDetailsDTO> paginate(Pageable pageable);
    FollowUpApplicationDetailsDTO findById(Integer id);
    FollowUpApplicationDetailsDTO create(FollowUpApplicationCreateUpdateDTO followUpApplicationCreateUpdateDTO);
    FollowUpApplicationDetailsDTO update(Integer id, FollowUpApplicationCreateUpdateDTO followUpApplicationCreateUpdateDTO);
    void delete(Integer id);
}
