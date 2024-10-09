package com.workconnect.mapper;


import com.workconnect.dto.JobPhaseCreateUpdateDTO;
import com.workconnect.dto.JobPhaseDetailsDTO;
import com.workconnect.model.entity.JobPhase;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class JobPhaseMapper {
    private final ModelMapper modelMapper;
    public JobPhaseMapper(ModelMapper modelMapper){this.modelMapper=modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public JobPhaseDetailsDTO toDetailsDTO(JobPhase jobPhase) {
        JobPhaseDetailsDTO jobPhaseDetailsDTO =  modelMapper.map(jobPhase, JobPhaseDetailsDTO.class);
        jobPhaseDetailsDTO.setJobName(jobPhase.getJob().getTitle());
        return jobPhaseDetailsDTO;
    }

    public JobPhase toEntity(JobPhaseCreateUpdateDTO jobPhaseCreateUpdateDTO) {
        return modelMapper.map(jobPhaseCreateUpdateDTO, JobPhase.class);
    }

    public JobPhaseCreateUpdateDTO toCreateUpdateDTO(JobPhase jobPhase) {
        return modelMapper.map(jobPhase, JobPhaseCreateUpdateDTO.class);
    }

}
