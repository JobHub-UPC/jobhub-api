package com.workconnect.model.entity;

import com.workconnect.model.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription_plan")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole targetAudience;
}
