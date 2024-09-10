package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "forum_comments")
public class ForumComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer forumCommentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @Column(name = "likes_count")
    private Float likesCount;
}
