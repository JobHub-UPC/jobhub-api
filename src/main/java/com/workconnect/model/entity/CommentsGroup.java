package com.workconnect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments_group")
public class CommentsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @Column(name = "likes_count")
    private Integer likesCount;

    //@JsonIgnore

    //Muchos comentarios se asocian a una persona
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "user",
                foreignKey = @ForeignKey(name = "FK_comments_member"))
    private Members members;
}
