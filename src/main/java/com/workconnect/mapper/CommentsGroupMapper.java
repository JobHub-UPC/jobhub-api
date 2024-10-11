package com.workconnect.mapper;

import com.workconnect.dto.CommentsGroupCreateUpdateDTO;
import com.workconnect.dto.CommentsGroupDetailsDTO;
import com.workconnect.model.entity.CommentsGroup;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentsGroupMapper {
    private final ModelMapper modelMapper;

    public CommentsGroupMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
    }
    public CommentsGroup toEntity(CommentsGroupCreateUpdateDTO commentsGroupCreateUpdateDTO) {
        return modelMapper.map(commentsGroupCreateUpdateDTO, CommentsGroup.class);
    }
    public CommentsGroupDetailsDTO toDetailsDTO(CommentsGroup commentsGroup) {
        return modelMapper.map(commentsGroup, CommentsGroupDetailsDTO.class);
    }
}
