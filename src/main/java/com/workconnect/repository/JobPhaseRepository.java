package com.workconnect.repository;

import com.workconnect.dto.JobPhaseDetailsDTO;
import com.workconnect.model.entity.JobPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPhaseRepository extends JpaRepository<JobPhase,Integer> {

    List<JobPhase> findJobPhasesByJobId(Integer JobId);
}
