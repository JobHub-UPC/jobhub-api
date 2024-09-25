package com.workconnect.service;


import com.workconnect.dto.CommunityDTO;
import com.workconnect.model.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCommunityService {
    List<CommunityDTO> getAll();
    Page<CommunityDTO> paginate(Pageable pageable);
    CommunityDTO findById(Integer id);
    CommunityDTO create(CommunityDTO communityDTO);
    CommunityDTO update(Integer id, CommunityDTO communityDTO);
    void delete(Integer id);

}
