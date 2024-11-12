package com.workconnect.dto;

import com.workconnect.model.enums.OrderPhase;
import lombok.Data;

@Data
public class JobPhaseDetailsDTO {
    private Integer id;
    private String name;
    private OrderPhase orderPhase;
    private String jobName;
}
