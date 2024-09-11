package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;

    @Column(name = "active_account", nullable = false)
    private boolean activeAccount;

    @OneToMany(mappedBy = "user")
    private Set<Member> members;

    @OneToMany(mappedBy = "user")
    private Set<Application> applications;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToOne(mappedBy = "user")
    private Applicant applicant;
}
