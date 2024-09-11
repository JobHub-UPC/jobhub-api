package com.workconnect.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Integer idGroup;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "members_count", nullable = false)
    private Integer membersCount;

    @Column(name = "is_private", nullable = false)
    private boolean isPrivate;

    @OneToMany(mappedBy = "group")
    private Set<Member> members;

}
