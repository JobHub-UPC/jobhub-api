package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer idJob;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "job_type", length = 50, nullable = false)
    private String jobType;

    @Column(name = "salary_range", nullable = false)
    private Double salaryRange;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id_company",
            foreignKey = @ForeignKey(name = "FK_job_company"))
    private Company company;

    @OneToMany(mappedBy = "job")
    private Set<Application> applications;
}
