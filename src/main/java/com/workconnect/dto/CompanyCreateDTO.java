package com.workconnect.dto;

import lombok.Data;

@Data
public class CompanyCreateDTO {
    private String name;
    private String description;
    private String email;
    private String phone;
    private String website;
    private String country;

    //private Integer userId;
    private String password;
}
