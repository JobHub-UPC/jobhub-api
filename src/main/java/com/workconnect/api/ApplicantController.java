package com.workconnect.api;

import com.workconnect.dto.ApplicantCreateDTO;

import com.workconnect.dto.ApplicantDetailsDTO;
import com.workconnect.dto.ApplicantUpdateDTO;

import com.workconnect.service.ApplicantService;
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
@RequestMapping("/admin/applicants")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('Admin','Company','Applicant')")
public class ApplicantController {
    private final ApplicantService applicantService;

    @GetMapping
    public ResponseEntity<List<ApplicantDetailsDTO>> listAll() {
        List<ApplicantDetailsDTO> applicants = applicantService.getAll();
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ApplicantDetailsDTO>> paginate(@PageableDefault(size = 5, sort = "firstName")
                                                    Pageable pageable) {
        Page<ApplicantDetailsDTO> page = applicantService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApplicantDetailsDTO> create(@Valid @RequestBody ApplicantCreateDTO applicantDTO) {
        ApplicantDetailsDTO createdApplicant = applicantService.create(applicantDTO);
        return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDetailsDTO> getApplicantById(@PathVariable Integer id) {
        ApplicantDetailsDTO applicant = applicantService.findById(id);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDetailsDTO> updateApplicant(@PathVariable Integer id, @RequestBody ApplicantUpdateDTO applicantDTO) {
        ApplicantDetailsDTO updatedApplicant = applicantService.update(id, applicantDTO);
        return new ResponseEntity<>(updatedApplicant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Integer id) {
        applicantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
