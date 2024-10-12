package com.workconnect.mapper;

import com.workconnect.dto.CompanyCreateDTO;
import com.workconnect.dto.CompanyUpdateDTO;
import com.workconnect.dto.CompanyDetailsDTO;
import com.workconnect.model.entity.Company;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public CompanyDetailsDTO toDetailsDto(Company company) {
        return modelMapper.map(company, CompanyDetailsDTO.class);
    }

    // Metodo para mapear desde CompanyCreateDTO
    public Company toEntity(CompanyCreateDTO companyCreateDTO) {
        return modelMapper.map(companyCreateDTO, Company.class);
    }

    // Metodo para mapear desde CompanyUpdateDTO
    public Company toEntity(CompanyUpdateDTO companyUpdateDTO) {
        return modelMapper.map(companyUpdateDTO, Company.class);
    }
}
