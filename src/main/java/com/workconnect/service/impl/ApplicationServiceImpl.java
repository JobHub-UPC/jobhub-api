package com.workconnect.service.impl;

import com.workconnect.dto.ApplicationReportDTO;
import com.workconnect.mapper.ApplicationMapper;
import com.workconnect.model.entity.Applicant;
import com.workconnect.model.entity.Application;
import com.workconnect.model.entity.Job;
import com.workconnect.repository.ApplicantRepository;
import com.workconnect.repository.ApplicationRepository;
import com.workconnect.repository.JobRepository;
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
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicationMapper applicationMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationReportDTO> getAll() {
        List<Application> applications = applicationRepository.findAll();
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toDetailsDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ApplicationReportDTO> paginate(Pageable pageable) {
        return applicationRepository.findAll(pageable)
                .map(applicationMapper::toDetailsDto);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicationReportDTO findById(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));
        return applicationMapper.toDetailsDto(application);
    }
/*
    @Transactional
    @Override
    public ApplicationReportDTO create(Application application) {

        Job job = jobRepository.findById(application.getJob().getId()).orElseThrow(()-> new RuntimeException("Job not founded with id: " + application.getJob().getId()));
        Applicant applicant = applicantRepository.findById(application.getApplicant().getId()).orElseThrow(()-> new RuntimeException("Applicant not found with id: " + application.getApplicant().getId()));

        application.setApplicant(applicant);
        application.setJob(job);
        return applicationRepository.save(application);
    }



    @Transactional
    @Override
    public Application update(Integer id, Application updateApplication) {
        Application applicationFromDb = findById(id);

        Job job = jobRepository.findById(updateApplication.getJob().getId()).orElseThrow(()-> new RuntimeException("Job not founded with id: " + updateApplication.getJob().getId()));
        Applicant applicant = applicantRepository.findById(updateApplication.getApplicant().getId()).orElseThrow(()-> new RuntimeException("Applicant not found with id: " + updateApplication.getApplicant().getId()));

        applicationFromDb.setDateCreated(LocalDateTime.now());
        applicationFromDb.setApplicant(applicant);
        applicationFromDb.setJob(job);
        return applicationRepository.save(applicationFromDb);
    }

 */

    @Transactional
    @Override
    public void delete(Integer id) {
        Application application = applicationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Application not founded with id: " + id));
        applicationRepository.delete(application);
    }
}
