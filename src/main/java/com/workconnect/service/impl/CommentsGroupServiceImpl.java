package com.workconnect.service.impl;

import com.workconnect.dto.CommentsGroupCreateUpdateDTO;
import com.workconnect.dto.CommentsGroupDetailsDTO;
import com.workconnect.mapper.CommentsGroupMapper;
import com.workconnect.model.entity.CommentsGroup;
import com.workconnect.model.entity.Member;
import com.workconnect.repository.CommentsGroupRepository;
import com.workconnect.repository.MemberRepository;
import com.workconnect.service.CommentsGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsGroupServiceImpl implements CommentsGroupService {
    private final CommentsGroupRepository commentsGroupRepository;
    private final MemberRepository memberRepository;
    private final CommentsGroupMapper commentsGroupMapper;
    @Transactional(readOnly = true)
    @Override
    public List<CommentsGroupDetailsDTO> getAll(){
        List<CommentsGroup> commentsGroups = commentsGroupRepository.findAll();
        return commentsGroups.stream().map(commentsGroupMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentsGroupDetailsDTO> paginate(Pageable pageable){
        Page<CommentsGroup> commentsGroups = commentsGroupRepository.findAll(pageable);
        return commentsGroups.map(commentsGroupMapper::toDetailsDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsGroupDetailsDTO findById(Integer id){
        CommentsGroup commentsGroup= commentsGroupRepository.findById(id).orElseThrow(
                ()->new RuntimeException("CommentsGroup Not Founded with id:" + id)
        );
        return commentsGroupMapper.toDetailsDTO(commentsGroup);
    }

    @Transactional
    @Override
    public CommentsGroupDetailsDTO create(CommentsGroupCreateUpdateDTO commentsGroupCreated){
        Member member = memberRepository.findById(commentsGroupCreated.getMemberId()).
                orElseThrow(()-> new RuntimeException("Member Not Founded with id:" + commentsGroupCreated.getMemberId()));
        CommentsGroup commentsGroup= commentsGroupMapper.toEntity(commentsGroupCreated);
        commentsGroup.setMember(member);
        commentsGroup.setPostedDate(LocalDateTime.now());
        return commentsGroupMapper.toDetailsDTO(commentsGroupRepository.save(commentsGroup));
    }

    @Transactional
    @Override
    public CommentsGroupDetailsDTO update(Integer id,CommentsGroupCreateUpdateDTO updateCommentsGroup){
        CommentsGroup commentsGroupFromDb=commentsGroupRepository.findById(id).orElseThrow(
                ()->new RuntimeException("CommentsGroup Not Founded with id:" + id)
        );
        Member member = memberRepository.findById(updateCommentsGroup.getMemberId())
                        .orElseThrow(()-> new RuntimeException("Member Not Founded with id:" + updateCommentsGroup.getMemberId()));
        commentsGroupFromDb.setPostedDate(LocalDateTime.now());
        commentsGroupFromDb.setMember(member);
        return commentsGroupMapper.toDetailsDTO(commentsGroupRepository.save(commentsGroupFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id){
        CommentsGroup commentsGroup = commentsGroupRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Comment Not Founded with id:" + id));
        commentsGroupRepository.delete(commentsGroup);
    }
}
