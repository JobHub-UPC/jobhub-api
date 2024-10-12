package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommunityDTO {
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    @Size(max=50, message = "Must be 50 characters long")
    private String name;
    @NotBlank(message = "Name is mandatory")
    @Size(max=1000, message = "Must be 1000 characters long")
    private  String description;

    private Boolean isPrivate;
}
