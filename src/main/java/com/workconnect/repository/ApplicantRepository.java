package com.workconnect.repository;

import com.workconnect.model.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    boolean existsByPhone(String phone);
    Optional<Applicant> findApplicantByEmail(String email);
    @Query("SELECT a.id FROM Applicant a WHERE a.user.id = :userId")
    Optional<Integer> findApplicantIdByUserId(@Param("userId") Integer userId);
}
