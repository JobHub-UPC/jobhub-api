package com.workconnect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments_applications")
public class CommentsApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;


    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "Fk_comments_application_id"))
    private User user;


    @JsonIgnore

    // Muchos comentarios se asocian a una aplicación
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_comments_application"))
    private Application application;
}
