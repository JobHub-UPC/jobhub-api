package com.workconnect.dto;

import com.workconnect.model.enums.ERole;
import lombok.Data;

@Data
public class CommentsApplicationDetailsDTO {
    private Integer id;
    private String comment;
    private ERole userRole;
    private Integer applicationId;
}
