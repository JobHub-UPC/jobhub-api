package com.workconnect.api;


import com.workconnect.model.entity.Job;
import com.workconnect.service.AdminJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminJobController {
    private final AdminJobService adminJobService;

    @GetMapping
    public ResponseEntity<List<Job>> listAllJobs() {
        List<Job>  jobs = adminJobService.getAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Job>> paginate(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        Page<Job> page = adminJobService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = adminJobService.create(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable Integer id) {
        Job job = adminJobService.findById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Integer id, @RequestBody Job job) {
        Job updatedJob = adminJobService.update(id, job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> delete(@PathVariable Integer id) {
        adminJobService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
