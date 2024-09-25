package com.workconnect.mapper;

import com.workconnect.dto.CommunityDTO;
import com.workconnect.model.entity.Community;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {
    private final ModelMapper modelMapper;

    public CommunityMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public CommunityDTO toDTO (Community community){
        return  modelMapper.map(community, CommunityDTO.class);
    }

    public Community toEntity(CommunityDTO communityDTO){
        return modelMapper.map(communityDTO, Community.class);
    }

}
