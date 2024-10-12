package com.workconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserResgistrationDTO {
    //Atributos en comun para Company y Applicant
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Country is required")
    private String country;
    //Atributos dentro de User
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    //Atributo especifico Company
    private String website;
    //Atributo especifico Applicant
    private String college;
}
