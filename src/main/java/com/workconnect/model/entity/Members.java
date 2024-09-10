package com.workconnect.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "members")
public class Members {

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "join_date")
    private LocalDateTime joinDate;
}
