package com.workconnect.service;

import com.workconnect.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminMemberService {
    List<Member> getAll();
    Page<Member> paginate(Pageable pageable);
    Member findById(Integer id);
    Member create(Member member);
    Member update(Integer id, Member updateMember);
    void delete(Integer id);
}
