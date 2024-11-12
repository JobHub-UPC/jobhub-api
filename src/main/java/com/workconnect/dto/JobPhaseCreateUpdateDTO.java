package com.workconnect.dto;

import com.workconnect.model.enums.OrderPhase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobPhaseCreateUpdateDTO {
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private Integer jobId;
    private OrderPhase orderPhase;
}
