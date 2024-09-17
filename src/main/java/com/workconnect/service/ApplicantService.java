package com.workconnect.service;

import com.workconnect.model.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantService {
    List<Applicant> getAll();
    Page<Applicant> paginate(Pageable pageable);
    Applicant findById(Integer id);
    Applicant create(Applicant applicant);
    Applicant update(Integer id, Applicant updateApplicant);
    void delete(Integer id);
}
