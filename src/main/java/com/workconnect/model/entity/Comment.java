package com.workconnect.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer idComment;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    @Column(name = "likes_count", nullable = false)
    private Integer likesCount;

    @ManyToOne
    @JoinColumn(name = "members_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_comment_member"))
    private Member member;
}
