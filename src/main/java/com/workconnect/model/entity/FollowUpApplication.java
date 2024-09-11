package com.workconnect.model.entity;

import com.workconnect.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "follow_up_application")
public class FollowUpApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_follow_up_application")
    private Integer idFollowUpApplication;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "last_update", nullable = false)
    private LocalDate lastUpdate;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id_application",
            foreignKey = @ForeignKey(name = "FK_followup_application"))
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @OneToOne
    @JoinColumn(name = "qualification_id", referencedColumnName = "id_qualification",
            foreignKey = @ForeignKey(name = "FK_followup_qualification"))
    private ApplicationQualification idQualification;
}



