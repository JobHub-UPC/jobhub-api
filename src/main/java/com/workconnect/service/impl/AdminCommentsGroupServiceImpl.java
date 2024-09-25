package com.workconnect.service.impl;

import com.workconnect.model.entity.CommentsGroup;
import com.workconnect.model.entity.Member;
import com.workconnect.repository.CommentsGroupRepository;
import com.workconnect.repository.MemberRepository;
import com.workconnect.service.AdminCommentsGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommentsGroupServiceImpl implements AdminCommentsGroupService {
    private final CommentsGroupRepository commentsGroupRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommentsGroup> getAll(){
        return commentsGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentsGroup> paginate(Pageable pageable){
        return commentsGroupRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsGroup findById(Integer id){
        return commentsGroupRepository.findById(id).orElseThrow(
                ()->new RuntimeException("CommentsGroup Not Founded with id:" + id)
        );
    }

    @Transactional
    @Override
    public CommentsGroup create(CommentsGroup commentsGroup){
        Member member = memberRepository.findById(commentsGroup.getMember().getId()).
                orElseThrow(()-> new RuntimeException("Member Not Founded with id:" + commentsGroup.getMember().getId()));
        commentsGroup.setMember(member);
        return commentsGroupRepository.save(commentsGroup);
    }

    @Transactional
    @Override
    public CommentsGroup update(Integer id,CommentsGroup updateCommentsGroup){
        CommentsGroup commentsGroupFromDb=findById(id);

        Member member = memberRepository.findById(updateCommentsGroup.getMember().getId())
                        .orElseThrow(()-> new RuntimeException("Member Not Founded with id:" + updateCommentsGroup.getMember().getId()));

        commentsGroupFromDb.setContent(updateCommentsGroup.getContent());
        commentsGroupFromDb.setLikesCount(updateCommentsGroup.getLikesCount());
        commentsGroupFromDb.setPostedDate(updateCommentsGroup.getPostedDate());
        commentsGroupFromDb.setMember(member);
        return commentsGroupRepository.save(commentsGroupFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        CommentsGroup commentsGroup = commentsGroupRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Comment Not Founded with id:" + id));
        commentsGroupRepository.delete(commentsGroup);
    }
}
