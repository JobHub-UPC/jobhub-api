package com.workconnect.dto;

import lombok.Data;

@Data
public class CommentsApplicationDetailsDTO {
    private Integer id;
    private String comment;
    private String userRole;
    private Integer applicationId;
}
