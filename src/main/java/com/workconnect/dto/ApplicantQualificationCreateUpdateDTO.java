package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicantQualificationCreateUpdateDTO {
    private Integer id;
    @NotBlank(message = "Qualification is mandatory")
    private Integer level;
    @NotBlank(message = "FollowUpQualification is mandatory")
    private Integer followUpApplication;
}
