package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobDetailsDTO {
    private Integer id;
    private Float salary;
    private LocalDateTime postedDate;
    private LocalDateTime deadlineDate;
    private String description;
    private String jobType;
    private String location;
    private String title;

    private String companyName;
}
