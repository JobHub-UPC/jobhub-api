package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "members")
@IdClass(MembersPK.class)
public class Members {
    @Id
    private Integer user;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "join_date")
    private LocalDateTime joinDate;
}
