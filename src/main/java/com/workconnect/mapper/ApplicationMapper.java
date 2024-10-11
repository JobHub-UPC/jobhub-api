package com.workconnect.mapper;

import com.workconnect.dto.ApplicationReportDTO;
import com.workconnect.model.entity.Application;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    private final ModelMapper modelMapper;

    public ApplicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ApplicationReportDTO toDetailsDto(Application application) {
        ApplicationReportDTO applicationReportDTO = modelMapper.map(application, ApplicationReportDTO.class);
        applicationReportDTO.setApplicantName(application.getApplicant().getFirstName() + " " + application.getApplicant().getLastName());
        applicationReportDTO.setApplicantID(application.getApplicant().getId());
        applicationReportDTO.setDegree(application.getApplicant().getDegree());
        applicationReportDTO.setApplicationDate(application.getDateCreated().toLocalDate());
        applicationReportDTO.setCountry(application.getApplicant().getCountry());
        return applicationReportDTO;
    }

    public Application toEntity(ApplicationReportDTO applicationReportDTO) {
        return modelMapper.map(applicationReportDTO, Application.class);
    }
}
