package com.workconnect.repository;

import com.workconnect.model.entity.FollowUpApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowUpApplicationRepository extends JpaRepository<FollowUpApplication,Integer> {
    //void deleteByApplicationId(Integer applicationId); // Para eliminar registros por applicationId
    List<FollowUpApplication> findByApplicationId(Integer applicationId);
}
