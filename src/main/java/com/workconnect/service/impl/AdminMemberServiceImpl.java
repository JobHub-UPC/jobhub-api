package com.workconnect.service.impl;

import com.workconnect.model.entity.Member;
import com.workconnect.model.entity.User;
import com.workconnect.repository.MemberRepository;
import com.workconnect.repository.UserRepository;
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
    public final UserRepository userRepository;

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
                ()-> new RuntimeException("Member Not founded with id: " + id)
        );
    }

    @Transactional
    @Override
    public Member create(Member member) {
        User user = userRepository.findById(member.getUser().getId())
                .orElseThrow(()-> new RuntimeException("User not found with id: " + member.getUser().getId()));
        member.setUser(user);
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public Member update(Integer id, Member updateMember) {
        Member memberFromDb = findById(id);

        User user = userRepository.findById(updateMember.getUser().getId())
                        .orElseThrow(()-> new RuntimeException("User not found with id: " + updateMember.getUser().getId()));

        memberFromDb.setIsAdmin(updateMember.getIsAdmin());
        memberFromDb.setComunity(updateMember.getComunity());
        memberFromDb.setJoinDate(LocalDateTime.now());
        memberFromDb.setUser(user);
        return memberRepository.save(memberFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Member Not founded with id: " + id));
        memberRepository.delete(member);
    }
}
