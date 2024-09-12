package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="job_phases")
public class JobPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "job_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_jobphase_job"))
    private Job job;
}
