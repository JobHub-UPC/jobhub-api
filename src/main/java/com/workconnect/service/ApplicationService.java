package com.workconnect.service;

import com.workconnect.model.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationService {
    List<Application> getAll();
    Page<Application> paginate(Pageable pageable);
    Application create(Application application);
    Application findById(Integer id);
    Application update(Integer id, Application updateApplication);
    void delete(Integer id);
}
