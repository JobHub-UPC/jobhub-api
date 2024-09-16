package com.workconnect.service.impl;

import com.workconnect.model.entity.Community;
import com.workconnect.repository.CommunityRepository;
import com.workconnect.service.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCommunityServiceImpl implements AdminCommunityService {

    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Community>getAll(){return communityRepository.findAll();}

    @Transactional(readOnly = true)
    @Override
    public Page<Community> paginate(Pageable pageable){return communityRepository.findAll(pageable);}

    @Transactional(readOnly = true)
    @Override
    public Community findById(Integer id){
        return communityRepository.findById(id).orElseThrow(()->new RuntimeException("Community not founded"));
    }

    @Transactional
    @Override
    public Community create(Community community){return communityRepository.save(community);}

    @Transactional
    @Override
    public Community update(Integer id, Community updatedCommunity){
        Community communityFromDb=findById(id);
        communityFromDb.setName(updatedCommunity.getName());
        communityFromDb.setDescription(updatedCommunity.getDescription());
        communityFromDb.setCreatedDate(updatedCommunity.getCreatedDate());
        communityFromDb.setIsPrivate(updatedCommunity.getIsPrivate());
        communityFromDb.setMembersCount(updatedCommunity.getMembersCount());
        return communityRepository.save(communityFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        Community community=findById(id);
        communityRepository.delete(community);
    }
}
