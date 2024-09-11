package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_application")
    private Integer idApplication;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id_job",
            foreignKey = @ForeignKey(name = "FK_application_job"))
    private Job job;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id_company",
            foreignKey = @ForeignKey(name = "FK_application_company"))
    private Company company;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id_applicant",
            foreignKey = @ForeignKey(name = "FK_application_applicant"))
    private Applicant applicant;

    @OneToMany(mappedBy = "application")
    private Set<FollowUpApplication> followUps;

    @OneToMany(mappedBy = "application")
    private Set<CommentsApplication> comments;
}
