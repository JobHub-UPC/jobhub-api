package com.workconnect.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicationReportDTO {
    private Integer id;
    private Integer applicantID;
    private String applicantName;
    private String degree;
    private String country;
    private LocalDate applicationDate;
}
