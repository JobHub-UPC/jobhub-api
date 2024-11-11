package com.workconnect.service;

import com.workconnect.dto.ApplicationCreateUpdateDTO;
import com.workconnect.dto.ApplicationReportDTO;
import com.workconnect.model.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationService {
    List<ApplicationReportDTO> getAll();
    Page<ApplicationReportDTO> paginate(Pageable pageable);
    ApplicationReportDTO create(ApplicationCreateUpdateDTO application);
    ApplicationReportDTO findById(Integer id);
    ApplicationReportDTO update(Integer id, ApplicationCreateUpdateDTO updateApplication);
    void delete(Integer id);
}
