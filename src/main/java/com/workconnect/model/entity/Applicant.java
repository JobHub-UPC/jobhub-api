package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applicant")
@IdClass(ApplicantPK.class)
public class Applicant {
    //PK compuesta
    @Id
    private Integer user;

    @Column(name = "fisrt_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, length = 50, unique = true)
    private String phone;

    @Column(name = "degree", nullable = false, length = 50)
    private String degree;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "college", nullable = false, length = 50)
    private String college;
}
