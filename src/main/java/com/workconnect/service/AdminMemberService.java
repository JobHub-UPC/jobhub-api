package com.workconnect.service;

import com.workconnect.dto.MemberCreateUpdateDTO;
import com.workconnect.dto.MemberReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMemberService {
    List<MemberReportDTO> getAll();
    Page<MemberReportDTO> paginate(Pageable pageable);
    MemberReportDTO findById(Integer id);
    MemberReportDTO create(MemberCreateUpdateDTO memberCreateUpdateDTO);
    MemberReportDTO update(Integer id, MemberCreateUpdateDTO updateMemberDTO);
    void delete(Integer id);
}
