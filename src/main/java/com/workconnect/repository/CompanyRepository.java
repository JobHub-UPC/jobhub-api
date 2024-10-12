package com.workconnect.repository;

import com.workconnect.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByPhone(String phone);
    Optional<Company> findByNameAndEmail(String name, String email);

}
