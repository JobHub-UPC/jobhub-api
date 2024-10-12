package com.workconnect.dto;

import lombok.Data;

@Data
public class FollowUpApplicationDetailsDTO {
    private Integer id;
    private String status;
    private String applicantName;
    private String jobPhaseName;
}
