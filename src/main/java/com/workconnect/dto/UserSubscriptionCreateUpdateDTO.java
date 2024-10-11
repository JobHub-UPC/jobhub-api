package com.workconnect.dto;

import com.workconnect.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSubscriptionCreateUpdateDTO {
    private Integer user_id;
    private Integer subscriptionPlan_id;
    private boolean isActive;
    private Integer months;
    private PaymentStatus paymentStatus;
    private LocalDateTime lastUpdated;
}
