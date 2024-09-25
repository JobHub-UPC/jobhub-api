package com.workconnect.service.impl;

import com.workconnect.model.entity.Company;
import com.workconnect.model.entity.Job;
import com.workconnect.repository.CompanyRepository;
import com.workconnect.repository.JobRepository;
import com.workconnect.service.AdminJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminJobServiceImpl implements AdminJobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Job> paginate(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Job findById(Integer id) {
        return jobRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Job not found with id: " + id)
        );
    }

    @Transactional
    @Override
    public Job create(Job job) {
        Company company = companyRepository.findById(job.getCompany().getId())
                .orElseThrow(()-> new RuntimeException("Company not found with id" + job.getCompany().getId()));
        job.setCompany(company);
        return jobRepository.save(job);
    }

    @Transactional
    @Override
    public Job update(Integer id, Job updateJob) {
        Job jobFromDB = findById(id);

        Company company = companyRepository.findById(updateJob.getCompany().getId())
                .orElseThrow(()-> new RuntimeException("Company not found with id" + updateJob.getCompany().getId()));

        jobFromDB.setJobType(updateJob.getJobType());
        jobFromDB.setDescription(updateJob.getDescription());
        jobFromDB.setLocation(updateJob.getLocation());
        jobFromDB.setDeadlineDate(updateJob.getDeadlineDate());
        jobFromDB.setPostedDate(updateJob.getPostedDate());
        jobFromDB.setSalary(updateJob.getSalary());
        jobFromDB.setTitle(updateJob.getTitle());
        jobFromDB.setCompany(company);
        return jobRepository.save(jobFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Job job = jobRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Job not found with id: " + id));
        jobRepository.delete(job);
    }
}
