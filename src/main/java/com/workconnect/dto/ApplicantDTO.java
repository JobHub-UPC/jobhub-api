package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicantDTO {
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Las name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(max = 9, message = "Phone must be 9 characters long")
    private String phone;

    @Size(max = 500, message = "Description must be 500 characters long")
    private String description;

    private String degree;
    private String country;
    private String college;



}
