package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Integer idCompany;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "website", length = 100)
    private String website;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobs;

    @OneToMany(mappedBy = "company")
    private Set<Application> applications;
}
