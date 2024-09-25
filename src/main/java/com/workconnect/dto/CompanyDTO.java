package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyDTO {
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 9, message = "Phone must be 9 characters long")
    private String phone;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be 500 characters long")
    private String description;

    private String country;
    private String website;
}
