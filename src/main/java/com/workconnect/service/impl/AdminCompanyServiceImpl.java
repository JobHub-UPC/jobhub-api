package com.workconnect.service.impl;

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
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }

    @Transactional
    @Override
    public Company create(Company company) {
        User user = userRepository.findById(company.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + company.getUser().getId()));
        company.setUser(user);
        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public Company update(Integer id, Company updateCompany) {
        Company companyFromDb = findById(id);

        User user = userRepository.findById(updateCompany.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + updateCompany.getUser().getId()));

        companyFromDb.setName(updateCompany.getName());
        companyFromDb.setDescription(updateCompany.getDescription());
        companyFromDb.setCountry(updateCompany.getCountry());
        companyFromDb.setWebsite(updateCompany.getWebsite());
        companyFromDb.setUser(user);
        return companyRepository.save(companyFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Company company = companyRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        companyRepository.delete(company);
    }
}
