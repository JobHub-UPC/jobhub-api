package com.workconnect.dto;

import lombok.Data;

@Data
public class ApplicantDetailsDTO {
    private Integer id;
    private String ApplicantName;
    private String email;
    private String phone;
    private String description;
    private String country;
    private String college;
    private String degree;
}
