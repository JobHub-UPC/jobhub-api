package com.workconnect.service.impl;

import com.workconnect.dto.CommunityDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.CommunityMapper;
import com.workconnect.model.entity.Community;
import com.workconnect.repository.CommunityRepository;
import com.workconnect.service.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCommunityServiceImpl implements AdminCommunityService {
    private final CommunityMapper communityMapper;
    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommunityDTO>getAll(){
        List<Community> communities=communityRepository.findAll();
        return communities.stream().map(communityMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommunityDTO> paginate(Pageable pageable) {
        Page<Community> communities = communityRepository.findAll(pageable);
        return communities.map(communityMapper::toDTO);
    }
    @Transactional(readOnly = true)
    @Override
    public CommunityDTO findById(Integer id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El autor con ID "+id+" no fue encontrado"));
        return communityMapper.toDTO(community);
    }

    @Transactional
    @Override
    public CommunityDTO create(CommunityDTO communityDTO) {
        Community community = communityMapper.toEntity(communityDTO);
        community.setCreatedDate(LocalDateTime.now());
        community = communityRepository.save(community);
        return communityMapper.toDTO(community);
    }
    @Transactional
    @Override
    public CommunityDTO update(Integer id, CommunityDTO updatedCommunityDTO){
        Community communityFromDb = communityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El autor con ID " + id + " no fue encontrado"));


        // Actualizar los campos
        communityFromDb.setName(updatedCommunityDTO.getName());
        communityFromDb.setDescription(updatedCommunityDTO.getDescription());
        communityFromDb.setIsPrivate(updatedCommunityDTO.getIsPrivate());

        communityFromDb = communityRepository.save(communityFromDb);
        return communityMapper.toDTO(communityFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id){
        Community community=communityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El grupo con ID " + id + " no fue encontrado"));
        communityRepository.delete(community);
    }
}
