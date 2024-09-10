package com.workconnect.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "company")
public class Company {
    private String companyName;
    private String companyAddress;
    private String companyPhone;

}
