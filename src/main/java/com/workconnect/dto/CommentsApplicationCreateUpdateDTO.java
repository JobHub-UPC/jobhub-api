package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentsApplicationCreateUpdateDTO {
    private Integer id;
    private String comment;
    private Integer userId;
    private Integer applicationId;
}
