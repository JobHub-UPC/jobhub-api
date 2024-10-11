package com.workconnect.dto;

import com.workconnect.model.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FollowUpApplicationCreateUpdateDTO {

    private Integer id;
    private Integer applicantId;
    @NotBlank(message = "Status is mandatory")
    private ApplicationStatus status;
    private Integer jobPhaseId;
}
