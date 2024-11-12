package com.workconnect.repository;

import com.workconnect.model.entity.FollowUpApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowUpApplicationRepository extends JpaRepository<FollowUpApplication,Integer> {
    //void deleteByApplicationId(Integer applicationId); // Para eliminar registros por applicationId
    List<FollowUpApplication> findByApplicationId(Integer applicationId);
    @Query("SELECT f.id FROM FollowUpApplication f WHERE f.application.id = :applicationId")
    Integer findFollowUpApplicationIdByApplicationId(@Param("applicationId") Integer applicationId);
    @Query("SELECT a.job.id\n" +
            "FROM FollowUpApplication f\n" +
            "JOIN Application a ON f.application.id = a.id\n" +
            "WHERE f.id = :followUpId")
    Integer findJobIdByFollowUpId(@Param("followUpId") Integer followUpId);
}
