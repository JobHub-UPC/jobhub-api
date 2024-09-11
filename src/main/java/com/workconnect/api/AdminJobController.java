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
@RequestMapping("/admin/Job")
@RequiredArgsConstructor
public class AdminJobController {
    private final AdminJobService adminJobService;


    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = adminJobService.getAll();
        return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Job>> paginateJobs(
            @PageableDefault(size =5, sort ="job_id")Pageable pageable){
        Page<Job> jobs = adminJobService.paginate(pageable);
        return new ResponseEntity<Page<Job>>(jobs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Integer id){
        Job job = adminJobService.findById(id);
        return new ResponseEntity<Job>(job,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job newJob = adminJobService.create(job);
        return new ResponseEntity<Job>(newJob,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") Integer id,
                                                   @RequestBody Job job){
        Job updateJob = adminJobService.update(id,job);
        return new ResponseEntity<Job>(updateJob,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable("id") Integer id){
        adminJobService.delete(id);
        return new ResponseEntity<Job>(HttpStatus.NO_CONTENT);
    }


}
