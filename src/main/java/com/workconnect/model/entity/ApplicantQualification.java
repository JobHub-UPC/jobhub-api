package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applicant_qualification")
public class ApplicantQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicantQualificationId;

    @Column(nullable = false)
    private Integer level;
}
