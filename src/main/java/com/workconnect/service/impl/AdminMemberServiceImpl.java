package com.workconnect.service.impl;

import com.workconnect.dto.MemberCreateUpdateDTO;
import com.workconnect.dto.MemberReportDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.MemberMapper;
import com.workconnect.model.entity.Member;
import com.workconnect.model.entity.User;
import com.workconnect.repository.CommentsGroupRepository;
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
    private final CommentsGroupRepository commentsGroupRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MemberReportDTO> getAll() {
        //List<Member> members = memberRepository.findAll();
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDetailsDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MemberReportDTO> paginate(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(memberMapper::toDetailsDto);
    }

    @Transactional(readOnly = true)
    @Override
    public MemberReportDTO findById(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Member not found with id " + id));
        return memberMapper.toDetailsDto(member);
    }

    @Transactional
    @Override
    public MemberReportDTO create(MemberCreateUpdateDTO memberCreateUpdateDTO) {

        User user = userRepository.findById(memberCreateUpdateDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + memberCreateUpdateDTO.getUserID()));

        Member member = memberMapper.toEntity(memberCreateUpdateDTO);
        member.setJoinDate(LocalDateTime.now());
        member.setUser(user);
        member = memberRepository.save(member);
        return memberMapper.toDetailsDto(member);
    }

    @Transactional
    @Override
    public MemberReportDTO update(Integer id, MemberCreateUpdateDTO updateMemberDTO) {
        Member memberFromDb = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));

        User user = userRepository.findById(updateMemberDTO.getUserID())
                        .orElseThrow(()-> new RuntimeException("User not found with id: " + updateMemberDTO.getUserID()));

        memberFromDb.setIsAdmin(updateMemberDTO.getIsAdmin());
        //memberFromDb.setComunity(updateMemberDTO.getCommunity());
        memberFromDb.setJoinDate(LocalDateTime.now());
        memberFromDb.setUser(user);
        memberFromDb = memberRepository.save(memberFromDb);
        return memberMapper.toDetailsDto(memberFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {

        //Delete comments
        commentsGroupRepository.deleteByMemberId(id);


        Member member = memberRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Member Not founded with id: " + id));
        memberRepository.delete(member);
    }
}
