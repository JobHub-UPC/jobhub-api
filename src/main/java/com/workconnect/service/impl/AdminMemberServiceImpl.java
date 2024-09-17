package com.workconnect.service.impl;

import com.workconnect.model.entity.Member;
import com.workconnect.repository.MemberRepository;
import com.workconnect.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminMemberServiceImpl implements AdminMemberService {

    public final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Member> paginate(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Member findById(Integer id) {
        return memberRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Member Not founded")
        );
    }

    @Transactional
    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public Member update(Integer id, Member updateMember) {
        Member memberFromDb = findById(id);
        memberFromDb.setIsAdmin(updateMember.getIsAdmin());
        memberFromDb.setComunity(updateMember.getComunity());
        memberFromDb.setJoinDate(LocalDateTime.now());
        return memberRepository.save(memberFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }
}
