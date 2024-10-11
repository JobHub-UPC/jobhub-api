package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentsGroupDetailsDTO {
    private Integer id;
    private String content;
    private Integer likesCount;
    private LocalDateTime postedDate;
    //private String memberName;
}
