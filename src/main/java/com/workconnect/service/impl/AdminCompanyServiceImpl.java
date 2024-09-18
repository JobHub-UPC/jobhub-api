package com.workconnect.service.impl;

import com.workconnect.model.entity.Company;
import com.workconnect.repository.CompanyRepository;
import com.workconnect.service.AdminCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCompanyServiceImpl implements AdminCompanyService {
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Company> paginate(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Company findById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Transactional
    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public Company update(Integer id, Company updateCompany) {
        Company companyFromDb = findById(id);
        companyFromDb.setName(updateCompany.getName());
        companyFromDb.setDescription(updateCompany.getDescription());
        companyFromDb.setCountry(updateCompany.getCountry());
        companyFromDb.setWebsite(updateCompany.getWebsite());
        return companyRepository.save(companyFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Company company = findById(id);
        companyRepository.delete(company);
    }
}
