package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "application_qualification")
public class ApplicationQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qualification")
    private Integer idQualification;

    @Column(name = "level", nullable = false)
    private Integer level;

    @OneToMany(mappedBy = "qualification")
    private Set<FollowUpApplication> followUps;
}
