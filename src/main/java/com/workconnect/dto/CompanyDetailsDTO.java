package com.workconnect.dto;

import lombok.Data;

@Data
public class CompanyDetailsDTO {
    private Integer id;
    private String name;
    private String description;
    private String email;
    private String phone;
    private String website;
    private String country;
}
