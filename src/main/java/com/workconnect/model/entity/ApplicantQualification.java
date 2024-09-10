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
}
