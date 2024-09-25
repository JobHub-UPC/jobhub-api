package com.workconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommunityDTO {
    private Integer id;
    @NotBlank(message = "Nombre del grupo Obliga")
    @Size(max=50, message = "Debe tener como maximo 50 caracteres")
    private String name;
    @NotBlank(message = "Nombre del grupo Obliga")
    @Size(max=1000, message = "Debe tener como maximo 1000 caracteres")
    private  String description;

    private Boolean isPrivate;
}
