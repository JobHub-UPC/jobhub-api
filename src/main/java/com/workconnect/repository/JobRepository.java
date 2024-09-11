package com.workconnect.repository;

import com.workconnect.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends  JpaRepository<Job, Integer> {

}
