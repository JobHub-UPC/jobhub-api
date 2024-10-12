package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberCreateUpdateDTO {
    //private Integer id;

    @NotBlank(message = "User ID is mandatory")
    private Integer userID;

    @NotBlank(message = "You must define whether you are admin")
    private Boolean isAdmin;

    private LocalDateTime joinDate;
}
