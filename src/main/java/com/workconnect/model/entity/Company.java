package com.workconnect.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Column(name = "company_name", nullable = false, unique = true)
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
