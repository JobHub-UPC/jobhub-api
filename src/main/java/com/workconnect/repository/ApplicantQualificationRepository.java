package com.workconnect.repository;


import com.workconnect.model.entity.ApplicantQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantQualificationRepository extends JpaRepository<ApplicantQualification,Integer> {
    List<ApplicantQualification> findByFollowUpApplicationId(Integer followUpApplicationId);

}
