package com.workconnect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @JsonIgnore

    //Relación entre tablas -> llaves foráneas FK
    //Muchas aplicaciones se asociaran con un trabajo
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_job_applications"))
    private Job job;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_applicant_application"))
    private Applicant applicant;


    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowUpApplication> followUpApplications;

}
