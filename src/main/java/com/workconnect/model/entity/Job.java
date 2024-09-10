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

    @Column(nullable = false)
    private String jobType;

    @Column(name = "posted_date", nullable = false)
    private LocalDateTime postedDate;

    @Column(name = "deadline_date", nullable = false)
    private LocalDateTime deadlineDate;

    @Column(nullable = false)
    private Float salary;
}
