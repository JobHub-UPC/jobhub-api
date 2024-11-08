package com.workconnect.service;

import com.workconnect.dto.SubscriptionPlanDetailsDTO;

import java.util.List;

public interface SubscriptionPlanService {
    List<SubscriptionPlanDetailsDTO> getSubscriptionPlanDetails();
    SubscriptionPlanDetailsDTO getSubscriptionPlanDetailsById(Integer id);
}
