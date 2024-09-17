package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "applicant_qualifications")
public class ApplicantQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer level;


    @OneToOne
    @JoinColumn(name = "followupapplication_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_applicantqualification_follow"))
    private FollowUpApplication followUpApplication;
}
