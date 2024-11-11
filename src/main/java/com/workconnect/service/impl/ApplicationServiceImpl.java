package com.workconnect.service.impl;

import com.workconnect.dto.ApplicationCreateUpdateDTO;
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

    @Override
    public ApplicationReportDTO create(ApplicationCreateUpdateDTO application) {
        Application newApplication = applicationMapper.toEntity(application);
        Job job = jobRepository.findById(application.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + application.getJobId()));
        Applicant applicant = applicantRepository.findById(application.getApplicantId()).orElseThrow(() -> new RuntimeException("Applicant not found with id: " + application.getApplicantId()));
        newApplication.setJob(job);
        newApplication.setApplicant(applicant);
        newApplication.setDateCreated(LocalDateTime.now());
        return applicationMapper.toDetailsDto(applicationRepository.save(newApplication));
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicationReportDTO findById(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Application not found"));
        return applicationMapper.toDetailsDto(application);
    }

    @Override
    public ApplicationReportDTO update(Integer id, ApplicationCreateUpdateDTO updateApplication) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Application application = applicationRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Application not founded with id: " + id));
        applicationRepository.delete(application);
    }
}
