package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "companies")
//@IdClass(CompanyPK.class)
public class Company {

    @Id

    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String website;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_company_user"))
    private User user;


}
