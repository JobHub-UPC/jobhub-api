package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company")
@IdClass(CompanyPK.class)
public class Company {

    @Id
    private Integer user;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String website;

    @Column(columnDefinition = "TEXT")
    private String description;

}
