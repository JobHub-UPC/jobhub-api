package com.workconnect.mapper;

import com.workconnect.dto.CommentsApplicationCreateUpdateDTO;
import com.workconnect.dto.CommentsApplicationDetailsDTO;
import com.workconnect.model.entity.CommentsApplication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentsApplicationMapper {
    private final ModelMapper modelMapper;

    public CommentsApplicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
    }
    public CommentsApplication toEntity(CommentsApplicationCreateUpdateDTO commentsApplicationCreateUpdateDTO) {
        return modelMapper.map(commentsApplicationCreateUpdateDTO, CommentsApplication.class);
    }
    public CommentsApplicationDetailsDTO toDetailsDTO(CommentsApplication commentsApplication) {
        CommentsApplicationDetailsDTO commentsApplicationDetailsDTO = modelMapper.map(commentsApplication, CommentsApplicationDetailsDTO.class);
        commentsApplicationDetailsDTO.setUserRole(commentsApplication.getUser().getRole().getName());
        commentsApplicationDetailsDTO.setApplicationId(commentsApplication.getApplication().getId());
        return commentsApplicationDetailsDTO;
    }

}
