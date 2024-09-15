package com.workconnect.repository;

import com.workconnect.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Integer> {
}
