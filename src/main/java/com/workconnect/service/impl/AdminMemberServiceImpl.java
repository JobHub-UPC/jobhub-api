package com.workconnect.service.impl;

import com.workconnect.dto.MemberDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.MemberMapper;
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
    public final MemberMapper memberMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MemberDTO> getAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(memberMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MemberDTO> paginate(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(memberMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDTO findById(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Member not found with id " + id));
        return memberMapper.toDTO(member);
    }

    @Transactional
    @Override
    public MemberDTO create(MemberDTO memberDTO) {

        User user = userRepository.findById(memberDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + memberDTO.getId()));

        Member member = memberMapper.toEntity(memberDTO);
        member.setUser(user);
        member = memberRepository.save(member);
        return memberMapper.toDTO(member);
    }

    @Transactional
    @Override
    public MemberDTO update(Integer id, MemberDTO updateMemberDTO) {
        Member memberFromDb = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));

        User user = userRepository.findById(updateMemberDTO.getId())
                        .orElseThrow(()-> new RuntimeException("User not found with id: " + updateMemberDTO.getId()));

        memberFromDb.setIsAdmin(updateMemberDTO.getIsAdmin());
        //memberFromDb.setComunity(updateMemberDTO.getCommunity());
        memberFromDb.setJoinDate(LocalDateTime.now());
        memberFromDb.setUser(user);
        memberFromDb = memberRepository.save(memberFromDb);
        return memberMapper.toDTO(memberFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Member Not founded with id: " + id));
        memberRepository.delete(member);
    }
}
