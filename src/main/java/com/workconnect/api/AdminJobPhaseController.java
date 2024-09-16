package com.workconnect.api;

import com.workconnect.model.entity.JobPhase;
import com.workconnect.service.AdminJobPhaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/jobphases")
@RequiredArgsConstructor
public class AdminJobPhaseController {
    private final AdminJobPhaseService adminJobPhaseService;

    @GetMapping
    public ResponseEntity<List<JobPhase>> listAll() {
        List<JobPhase> jobPhases = adminJobPhaseService.getAll();
        return new ResponseEntity<>(jobPhases, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<JobPhase>> paginate(@PageableDefault(size = 5, sort = "firstName")
                                                 Pageable pageable) {
        Page<JobPhase> page = adminJobPhaseService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobPhase> create(@RequestBody JobPhase jobPhase) {
        JobPhase createdJobPhase = adminJobPhaseService.create(jobPhase);
        return new ResponseEntity<>(createdJobPhase, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPhase> getById(@PathVariable Integer id) {
        JobPhase jobPhase = adminJobPhaseService.findById(id);
        return new ResponseEntity<>(jobPhase, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPhase> update(@PathVariable Integer id, @RequestBody JobPhase jobPhase) {
        JobPhase updatedJobPhase = adminJobPhaseService.update(id, jobPhase);
        return new ResponseEntity<>(updatedJobPhase, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminJobPhaseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
