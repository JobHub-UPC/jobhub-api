package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberReportDTO {
    private Integer id;
    private String memberMail;
    private Boolean isAdmin;
    private Boolean isActive;
    private LocalDateTime joinDate;
    private String communityName;
    private String role;

}
