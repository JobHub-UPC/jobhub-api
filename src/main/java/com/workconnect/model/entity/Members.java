package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "members")
//@IdClass(MembersPK.class)
public class Members {
    @Id
    private Integer username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_members_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "comunity_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_members_user"))
    private Community comunity;


    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @ManyToOne
    @JoinColumn(name = "community_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_members_group"))
    private Community community;
}
