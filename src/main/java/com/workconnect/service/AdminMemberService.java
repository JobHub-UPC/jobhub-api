package com.workconnect.service;

import com.workconnect.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMemberService {
    List<MemberDTO> getAll();
    Page<MemberDTO> paginate(Pageable pageable);
    MemberDTO findById(Integer id);
    MemberDTO create(MemberDTO memberDTO);
    MemberDTO update(Integer id, MemberDTO updateMemberDTO);
    void delete(Integer id);
}
