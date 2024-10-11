package com.workconnect.dto;

import com.workconnect.model.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Integer id;
    private String user;
    private Integer community;
    private Boolean isAdmin;
    private LocalDateTime joinDate;

}
