package com.workconnect.model.entity;

import com.workconnect.model.enums.OrderPhase;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "orderPhase", nullable = false)
    private OrderPhase orderPhase;

    @ManyToOne
    @JoinColumn(name = "job_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_jobphase_job"))
    private Job job;
}
