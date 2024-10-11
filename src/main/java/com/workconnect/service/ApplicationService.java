package com.workconnect.service;

import com.workconnect.dto.ApplicationReportDTO;
import com.workconnect.model.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationService {
    List<ApplicationReportDTO> getAll();
    Page<ApplicationReportDTO> paginate(Pageable pageable);
    //ApplicationReportDTO create(Application application);
    ApplicationReportDTO findById(Integer id);
    //Application update(Integer id, Application updateApplication);
    void delete(Integer id);
}
