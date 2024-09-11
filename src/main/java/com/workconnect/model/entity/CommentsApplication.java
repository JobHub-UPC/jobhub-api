package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments_application")
public class CommentsApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private String author;

    private LocalDateTime created;

    // Muchos comentarios se asocian a una aplicación
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_comments_application"))
    private Application application;
}
