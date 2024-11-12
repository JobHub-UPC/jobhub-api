package com.workconnect.dto;

import com.workconnect.model.enums.ERole;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Integer id;
    private String email;
    private ERole role;
    private String phone;
    private String country;
    private String firstName;
    private String lastName;
    private String website;
    private String college;
    private String description;
    private String degree;
}
