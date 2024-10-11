package com.workconnect.dto;

import lombok.Data;


@Data
public class CommentsApplicationCreateUpdateDTO {
    private String comment;
    private Integer userId;
    private Integer applicationId;
}
