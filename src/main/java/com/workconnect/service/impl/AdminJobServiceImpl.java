package com.workconnect.service.impl;

import com.workconnect.dto.JobCreateUpdateDTO;
import com.workconnect.dto.JobDetailsDTO;
import com.workconnect.mapper.JobMapper;
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

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminJobServiceImpl implements AdminJobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    private final JobMapper jobMapper;

    @Transactional(readOnly = true)
    @Override
    public List<JobDetailsDTO> getAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toDetailsDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobDetailsDTO> paginate(Pageable pageable) {
        return jobRepository.findAll(pageable)
                .map(jobMapper::toDetailsDto);
    }

    @Transactional(readOnly = true)
    @Override
    public JobDetailsDTO findById(Integer id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found"));
        return jobMapper.toDetailsDto(job);
    }

    @Transactional
    @Override
    public JobDetailsDTO create(JobCreateUpdateDTO jobCreateUpdateDTO) {
        jobRepository.findById(jobCreateUpdateDTO.getId())
                .ifPresent(existingJob -> {
                    throw new RuntimeException("Job already exists");
                });

        // Verificar si la compañía no es nula
        if (jobCreateUpdateDTO.getCompanyID() == null) {
            throw new RuntimeException("Company is required to create a Job");
        }

        // Buscar la compañía por ID
        Company company = companyRepository.findById(jobCreateUpdateDTO.getCompanyID())
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + jobCreateUpdateDTO.getCompanyID()));

        Job job = jobMapper.toEntity(jobCreateUpdateDTO);

        if (job.getPostedDate() == null) {
            job.setPostedDate(LocalDateTime.now());
        }

        // Asignar la compañía al trabajo y guardar
        job.setCompany(company);

        return jobMapper.toDetailsDto(jobRepository.save(job));
    }


    @Transactional
    @Override
    public JobDetailsDTO update(Integer id, JobCreateUpdateDTO updateJobDTO) {
        Job jobFromDB = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        Company company = companyRepository.findById(updateJobDTO.getCompanyID())
                .orElseThrow(()-> new RuntimeException("Company not found with id" + updateJobDTO.getCompanyID()));


        jobFromDB.setJobType(updateJobDTO.getJobType());
        jobFromDB.setDescription(updateJobDTO.getDescription());
        jobFromDB.setLocation(updateJobDTO.getLocation());
        jobFromDB.setDeadlineDate(updateJobDTO.getDeadlineDate());
        //jobFromDB.setPostedDate(updateJobDTO.getPostedDate());
        jobFromDB.setSalary(updateJobDTO.getSalary());
        jobFromDB.setTitle(updateJobDTO.getTitle());
        jobFromDB.setCompany(company);

        jobFromDB = jobRepository.save(jobFromDB);
        return jobMapper.toDetailsDto(jobFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Job job = jobRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Job not found with id: " + id));
        jobRepository.delete(job);
    }
}
