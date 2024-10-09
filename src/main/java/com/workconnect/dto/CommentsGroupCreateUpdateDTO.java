package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentsGroupCreateUpdateDTO {
    private Integer id;
    private String content;
    private Integer likesCount;
    private Integer memberId;
}
