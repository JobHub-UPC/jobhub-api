package com.workconnect.service.impl;

import com.workconnect.model.entity.Application;
import com.workconnect.repository.ApplicationRepository;
import com.workconnect.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Application> paginate(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Application findById(Integer id) {
        return applicationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Application not founded")
        );
    }

    @Transactional
    @Override
    public Application create(Application application) {
        return applicationRepository.save(application);
    }

    @Transactional
    @Override
    public Application update(Integer id, Application updateApplication) {
        Application applicationFromDb = findById(id);
        applicationFromDb.setJob(updateApplication.getJob());
        applicationFromDb.setApplicant(updateApplication.getApplicant());
        applicationFromDb.setDateCreated(LocalDateTime.now());
        return applicationRepository.save(applicationFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Application application = findById(id);
        applicationRepository.delete(application);
    }
}
