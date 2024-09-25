package com.workconnect.repository;

import com.workconnect.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByNameAndEmail(String name, String email);

}
