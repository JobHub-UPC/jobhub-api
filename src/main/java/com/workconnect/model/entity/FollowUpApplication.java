package com.workconnect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workconnect.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "follow_up_applications")

public class FollowUpApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;
    @JsonIgnore

    // Un seguimiento de aplicación se asocian con una apicación
    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "FK_follow_application"))
    private Application application;


    @ManyToOne
    @JoinColumn(name ="jobphase_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_followupaplplication"))
    private JobPhase jobphase;

    // Relación de composición
    // En un seguimiento puede haber muchos estados
    //@OneToMany(mappedBy = "followUpApplication", cascade = CascadeType.ALL)
    //private List<ApplicationStatus> applicationStatuses;
}
