package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name =  "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "job_type", nullable = false)
    private String jobType;

    @Column(name = "posted_date", nullable = false)
    private LocalDateTime postedDate;

    @Column(name = "deadline_date", nullable = false)
    private LocalDateTime deadlineDate;

    @Column(nullable = false)
    private Float salary;

    // Muchos trabajos se ascocian a una sola empresa
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_jobs_company"))
    private Company company;
}
