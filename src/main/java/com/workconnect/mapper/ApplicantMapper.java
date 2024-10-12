package com.workconnect.mapper;

import com.workconnect.dto.ApplicantCreateDTO;
import com.workconnect.dto.ApplicantDTO;
import com.workconnect.dto.ApplicantDetailsDTO;
import com.workconnect.dto.ApplicantUpdateDTO;
import com.workconnect.model.entity.Applicant;
import com.workconnect.model.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {

    private final ModelMapper modelMapper;

    public ApplicantMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    public ApplicantDetailsDTO toDetailsDto(Applicant applicant) {
        ApplicantDetailsDTO dto = modelMapper.map(applicant, ApplicantDetailsDTO.class);
        dto.setApplicantName(applicant.getFirstName() + " " + applicant.getLastName());
      return dto;
    }

    public Applicant toEntity(ApplicantCreateDTO applicantCreateDTO) {
        return modelMapper.map(applicantCreateDTO, Applicant.class);
    }

    public Applicant toEntity(ApplicantUpdateDTO applicantUpdateDTO) {
        return modelMapper.map(applicantUpdateDTO, Applicant.class);
    }
}
