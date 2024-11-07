package com.workconnect.repository;

import com.workconnect.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByPhone(String phone);
    Optional<Company> findByNameAndEmail(String name, String email);
    @Query("SELECT c.id FROM Company c WHERE c.user.id = :userId")
    Optional<Integer> findCompanyIdByUserId(@Param("userId") Integer userId);
}
