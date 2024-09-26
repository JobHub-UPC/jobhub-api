package com.workconnect.repository;

import com.workconnect.model.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    Optional<Applicant> findApplicantByEmail(String email);
}
