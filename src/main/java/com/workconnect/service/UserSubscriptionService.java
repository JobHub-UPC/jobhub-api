package com.workconnect.service;

import com.workconnect.dto.UserSubscriptionCreateUpdateDTO;
import com.workconnect.dto.UserSubscriptionDTO;

import java.util.List;

public interface UserSubscriptionService {
    UserSubscriptionDTO createUserSubscription(UserSubscriptionCreateUpdateDTO userSubscriptionCreateUpdateDTO);
    List<UserSubscriptionDTO> getUserSubscriptionHistoryByUserId();
    List<UserSubscriptionDTO> getAllUserSubscription();
    UserSubscriptionDTO confirmUserSubscription(Integer purchaseId);
    UserSubscriptionDTO getUserSubscriptionById(Integer id);
}
