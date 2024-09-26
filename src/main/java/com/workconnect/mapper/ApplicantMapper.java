package com.workconnect.mapper;

import com.workconnect.dto.ApplicantDTO;
import com.workconnect.model.entity.Applicant;
import com.workconnect.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {

    private final ModelMapper modelMapper;

    public ApplicantMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ApplicantDTO toDTO(Applicant applicant) {
      ApplicantDTO applicantDTO =  modelMapper.map(applicant, ApplicantDTO.class);

     User user = modelMapper.map(applicant.getUser(), User.class);
     user.setEmail(applicantDTO.getEmail());
     user.setPassword(applicantDTO.getPassword());

      return applicantDTO;
    }

    public Applicant toEntity(ApplicantDTO applicantDTO) {
        return modelMapper.map(applicantDTO, Applicant.class);
    }
}
