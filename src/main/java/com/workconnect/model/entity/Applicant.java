package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_applicant")
    private Integer idApplicant;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "degree", length = 50)
    private String degree;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "college", length = 50)
    private String college;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_applicant_user"))
    private User user;

    @OneToMany(mappedBy = "applicant")
    private Set<Application> applications;
}
