package com.workconnect.mapper;

import com.workconnect.dto.JobCreateUpdateDTO;
import com.workconnect.dto.JobDetailsDTO;
import com.workconnect.model.entity.Job;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    private final ModelMapper modelMapper;
    public JobMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    //Mapeo para mostrar información
    public JobDetailsDTO toDetailsDto(Job job) {
        JobDetailsDTO jobDetailsDTO = modelMapper.map(job, JobDetailsDTO.class);

        jobDetailsDTO.setCompanyName(job.getCompany().getName());

        return jobDetailsDTO;
    }

    //Mapeo para crear - actualizar
    public Job toEntity(JobCreateUpdateDTO jobCreateUpdateDTO) {
        return modelMapper.map(jobCreateUpdateDTO, Job.class);
    }

    public JobCreateUpdateDTO toCreateUpdateDto(Job job) {
        return modelMapper.map(job, JobCreateUpdateDTO.class);
    }
}
