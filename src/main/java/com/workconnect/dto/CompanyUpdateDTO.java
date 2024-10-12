package com.workconnect.dto;

import lombok.Data;

@Data
public class CompanyUpdateDTO {
    private String name;
    private String description;
    private String phone;
    private String email;
    private String website;
    private String country;

    //private Integer userId;
}
