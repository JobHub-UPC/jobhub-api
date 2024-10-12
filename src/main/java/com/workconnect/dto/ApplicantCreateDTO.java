package com.workconnect.dto;

import lombok.Data;

@Data
public class ApplicantCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String description;
    private String country;
    private String college;
    private String degree;

    private String password;
}
