package com.workconnect.mapper;

import com.workconnect.dto.FollowUpApplicationCreateUpdateDTO;
import com.workconnect.dto.FollowUpApplicationDetailsDTO;
import com.workconnect.model.entity.FollowUpApplication;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class FollowUpApplicantionMapper {
    private final ModelMapper modelMapper;
    public FollowUpApplicantionMapper(ModelMapper modelMapper){this.modelMapper=modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public FollowUpApplicationDetailsDTO toDetailsDTO(FollowUpApplication followUpApplication) {
        FollowUpApplicationDetailsDTO followUpApplicationDetailsDTO =  modelMapper.map(followUpApplication, FollowUpApplicationDetailsDTO.class);

        followUpApplicationDetailsDTO.setApplicantName(followUpApplication.getApplication().getApplicant().getFirstName()+" "+followUpApplication.getApplication().getApplicant().getLastName());
        followUpApplicationDetailsDTO.setJobPhaseName(followUpApplication.getJobphase().getName());

        return followUpApplicationDetailsDTO;
    }

    public FollowUpApplication toEntity(FollowUpApplicationCreateUpdateDTO followUpApplicationCreateUpdateDTO) {
        return modelMapper.map(followUpApplicationCreateUpdateDTO, FollowUpApplication.class);
    }

    public FollowUpApplicationCreateUpdateDTO toCreateUpdateDTO(FollowUpApplication followUpApplication) {
        return modelMapper.map(followUpApplication, FollowUpApplicationCreateUpdateDTO.class);
    }

    // Mapeo de Book a BookDetailsDTO (para mostrar información completa)
    public FollowUpApplicationDetailsDTO toDetailsDto(FollowUpApplication followUpApplication) {
        FollowUpApplicationDetailsDTO followUpApplicationDetailsDTO = modelMapper.map(followUpApplication, FollowUpApplicationDetailsDTO.class);
        // Mapear manualmente el nombre del autor concatenando firstName y lastName
        followUpApplicationDetailsDTO.setApplicantName(followUpApplication.getApplication().getApplicant().getFirstName() + " " + followUpApplication.getApplication().getApplicant().getLastName());
        // Mapear manualmente el nombre de la categoría
        followUpApplicationDetailsDTO.setJobPhaseName(followUpApplication.getJobphase().getName());
        return followUpApplicationDetailsDTO;
    }



}
