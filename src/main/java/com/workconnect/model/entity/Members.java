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

    //@EmbeddedId
    //private MembersPK id;  // Usa la clase ApplicantPK como clave primaria


    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_members_group"))
    private Group group;
}
