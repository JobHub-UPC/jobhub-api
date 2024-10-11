package com.workconnect.api;


import com.workconnect.dto.JobCreateUpdateDTO;
import com.workconnect.dto.JobDetailsDTO;
import com.workconnect.service.AdminJobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/jobs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Company')")
public class AdminJobController {
    private final AdminJobService adminJobService;

    @GetMapping
    public ResponseEntity<List<JobDetailsDTO>> listAllJobs() {
        List<JobDetailsDTO>  jobs = adminJobService.getAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<JobDetailsDTO>> paginate(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        Page<JobDetailsDTO> page = adminJobService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobDetailsDTO> createJob(@RequestBody JobCreateUpdateDTO jobDTO) {
        JobDetailsDTO createdJob = adminJobService.create(jobDTO);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDetailsDTO> getById(@PathVariable Integer id) {
        JobDetailsDTO job = adminJobService.findById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDetailsDTO> update(@PathVariable Integer id, @Valid @RequestBody JobCreateUpdateDTO jobDTO) {
        JobDetailsDTO updatedJob = adminJobService.update(id, jobDTO);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminJobService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
