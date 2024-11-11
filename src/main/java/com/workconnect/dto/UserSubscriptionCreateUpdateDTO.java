package com.workconnect.dto;

import com.workconnect.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSubscriptionCreateUpdateDTO {
    private Integer subscriptionPlan_id;
    private Integer months;
}
