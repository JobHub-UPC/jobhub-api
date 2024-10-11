package com.workconnect.dto;

import com.workconnect.model.enums.ERole;
import lombok.Data;

@Data
public class UserSubscriptionDTO {
    private Integer id;
    private Integer user_id;
    private ERole role;
    private Integer subscriptionPlan_id;
    private Integer months;
    private String paymentStatus;
    private String lastUpdated;
    private Double amount;
}
