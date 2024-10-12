package com.workconnect.model.entity;

import com.workconnect.model.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name",nullable = false,unique = true)
    private ERole name;
}
