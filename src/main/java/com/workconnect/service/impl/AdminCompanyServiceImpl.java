package com.workconnect.service.impl;

import com.workconnect.dto.CompanyDTO;
import com.workconnect.exception.BadRequestException;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.CompanyMapper;
import com.workconnect.model.entity.Company;
import com.workconnect.model.entity.User;
import com.workconnect.repository.CompanyRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.AdminCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCompanyServiceImpl implements AdminCompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyMapper companyMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CompanyDTO> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CompanyDTO> paginate(Pageable pageable) {
        Page<Company> companies = companyRepository.findAll(pageable);
        return companies.map(companyMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyDTO findById(Integer id) {
        Company company =  companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return companyMapper.toDTO(company);
    }

    @Transactional
    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        companyRepository.findByNameAndEmail(companyDTO.getName(), companyDTO.getEmail())
                .ifPresent(existingCompany -> {
                    throw new BadRequestException("Company already exists");
                });

        User user = userRepository.findById(companyDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + companyDTO.getId()));

        Company company = companyMapper.toEntity(companyDTO);
        company.setUser(user);
        company = companyRepository.save(company);
        return companyMapper.toDTO(company);
    }

    @Transactional
    @Override
    public CompanyDTO update(Integer id, CompanyDTO updateCompanyDTO) {
        Company companyFromDb = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        //User user = userRepository.findById(updateCompanyDTO.getUser().getId())
        //                .orElseThrow(() -> new RuntimeException("User not found with id: " + updateCompanyDTO.getUser().getId()));

        User user = userRepository.findById(updateCompanyDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updateCompanyDTO.getId()));

        companyRepository.findByNameAndEmail(updateCompanyDTO.getName(), updateCompanyDTO.getEmail())
                .filter(existingCompany -> !existingCompany.getId().equals(id))
                .ifPresent(existingCompany -> {
                    throw new BadRequestException("Company already exists");
                });

        companyFromDb.setName(updateCompanyDTO.getName());
        companyFromDb.setDescription(updateCompanyDTO.getDescription());
        companyFromDb.setCountry(updateCompanyDTO.getCountry());
        companyFromDb.setWebsite(updateCompanyDTO.getWebsite());
        companyFromDb.setUser(user);

        companyFromDb = companyRepository.save(companyFromDb);
        return companyMapper.toDTO(companyFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Company company = companyRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        companyRepository.delete(company);
    }
}
