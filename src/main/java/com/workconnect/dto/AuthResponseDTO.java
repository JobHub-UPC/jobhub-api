package com.workconnect.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String phone;
    private String role;
}
