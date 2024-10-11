package com.workconnect.repository;

import com.workconnect.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    //List<Job> findTop6ByOrderByCreatedAtDecs();
}
