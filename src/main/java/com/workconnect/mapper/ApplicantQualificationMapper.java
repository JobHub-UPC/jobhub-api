package com.workconnect.mapper;

import com.workconnect.dto.ApplicantQualificationCreateUpdateDTO;
import com.workconnect.dto.ApplicantQualificationDetailsDTO;
import com.workconnect.model.entity.ApplicantQualification;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicantQualificationMapper {
    private final ModelMapper modelMapper;

    public ApplicantQualificationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
    }
    public ApplicantQualificationDetailsDTO toDetailsDTO(ApplicantQualification applicantQualification) {
        ApplicantQualificationDetailsDTO applicantQualificationDetailsDTO= modelMapper.map(applicantQualification, ApplicantQualificationDetailsDTO.class);
        applicantQualificationDetailsDTO.setApplicantName(applicantQualification.getFollowUpApplication().getApplication().getApplicant().getFirstName());
        applicantQualificationDetailsDTO.setPhase(applicantQualification.getFollowUpApplication().getJobphase().getName());
        return applicantQualificationDetailsDTO;
    }
    public ApplicantQualification toEntity(ApplicantQualificationCreateUpdateDTO applicantQualificationCreateUpdateDTO) {
        return modelMapper.map(applicantQualificationCreateUpdateDTO, ApplicantQualification.class);
    }
}
