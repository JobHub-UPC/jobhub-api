package com.workconnect.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comments_application")
public class CommentsApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer idComment;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id_application",
            foreignKey = @ForeignKey(name = "FK_comment_application"))
    private Application application;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_comment_user"))
    private User user;
}
