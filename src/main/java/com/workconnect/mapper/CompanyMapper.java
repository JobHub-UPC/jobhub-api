package com.workconnect.mapper;

import com.workconnect.dto.CompanyDTO;
import com.workconnect.model.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CompanyDTO toDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    public Company toEntity(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }
}
