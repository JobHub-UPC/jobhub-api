package com.workconnect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workconnect.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "follow_up_application")

public class FollowUpApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @JsonIgnore

    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "FK_follow_application"))
    private Application application;

    @OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "FK_follow_application_job"))
    private Job job;

    @OneToOne
    @JoinColumn(name = "application_qualification_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "FK_follow_application_qualification"))
    private ApplicantQualification applicantQualification;

}
