package com.workconnect.api;

import com.workconnect.model.entity.Applicant;
import com.workconnect.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    @GetMapping
    public ResponseEntity<List<Applicant>> listAll() {
        List<Applicant> applicants = applicantService.getAll();
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Applicant>> paginate(@PageableDefault(size = 5, sort = "firstName")
                                                    Pageable pageable) {
        Page<Applicant> page = applicantService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Applicant> create(@RequestBody Applicant applicant) {
        Applicant createdApplicant = applicantService.create(applicant);
        return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Integer id) {
        Applicant applicant = applicantService.findById(id);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable Integer id, @RequestBody Applicant applicant) {
        Applicant updatedApplicant = applicantService.update(id, applicant);
        return new ResponseEntity<>(updatedApplicant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Integer id) {
        applicantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
