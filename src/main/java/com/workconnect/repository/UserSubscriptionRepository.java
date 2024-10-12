package com.workconnect.repository;

import com.workconnect.model.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription,Integer> {
    List<UserSubscription> findByUserId(Integer userId);

}
