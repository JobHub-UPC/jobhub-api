package com.workconnect.api;

import com.workconnect.dto.JobPhaseCreateUpdateDTO;
import com.workconnect.dto.JobPhaseDetailsDTO;
import com.workconnect.service.JobPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobphases")
@RequiredArgsConstructor
public class JobPhaseController {
    private final JobPhaseService jobPhaseService;

    @GetMapping
    public ResponseEntity<List<JobPhaseDetailsDTO>> listAll() {
        List<JobPhaseDetailsDTO> jobPhases = jobPhaseService.getAll();
        return new ResponseEntity<>(jobPhases, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<JobPhaseDetailsDTO>> paginate(@PageableDefault(size = 5, sort = "Name")
                                                 Pageable pageable) {
        Page<JobPhaseDetailsDTO> page = jobPhaseService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobPhaseDetailsDTO> create(@RequestBody JobPhaseCreateUpdateDTO jobPhase) {
        JobPhaseDetailsDTO createdJobPhase = jobPhaseService.create(jobPhase);
        return new ResponseEntity<>(createdJobPhase, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPhaseDetailsDTO> getById(@PathVariable Integer id) {
        JobPhaseDetailsDTO jobPhase = jobPhaseService.findById(id);
        return new ResponseEntity<>(jobPhase, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPhaseDetailsDTO> update(@PathVariable Integer id, @RequestBody JobPhaseCreateUpdateDTO jobPhase) {
        JobPhaseDetailsDTO updatedJobPhase = jobPhaseService.update(id, jobPhase);
        return new ResponseEntity<>(updatedJobPhase, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jobPhaseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
