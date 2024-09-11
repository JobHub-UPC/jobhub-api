package com.workconnect.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ApplicantPK implements Serializable {
    @OneToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_applicant_user"))
    private User user;
}
