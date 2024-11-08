package com.workconnect.dto;

import com.workconnect.model.enums.ERole;
import lombok.Data;

@Data
public class SubscriptionPlanDetailsDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private ERole targetAudience;
}
