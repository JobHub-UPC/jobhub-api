package com.workconnect.api;

import com.workconnect.dto.ApplicantQualificationCreateUpdateDTO;
import com.workconnect.dto.ApplicantQualificationDetailsDTO;
import com.workconnect.service.ApplicantQualificationService;
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
@RequestMapping("/applicant-qualifications")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Company')")
public class ApplicantQualificationController {
    private final ApplicantQualificationService applicantQualificationService;

    @GetMapping
    public ResponseEntity<List<ApplicantQualificationDetailsDTO>> listAll(){
        List<ApplicantQualificationDetailsDTO> applicantQualifications= applicantQualificationService.getAll();
        return new ResponseEntity<>(applicantQualifications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ApplicantQualificationDetailsDTO>> paginate(@PageableDefault(size = 5,sort = "level")
        Pageable pageable){
        Page<ApplicantQualificationDetailsDTO> page= applicantQualificationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ApplicantQualificationDetailsDTO> create(@RequestBody ApplicantQualificationCreateUpdateDTO applicantQualification){
        ApplicantQualificationDetailsDTO createdApplicationQualification= applicantQualificationService.create(applicantQualification);
        return new ResponseEntity<>(createdApplicationQualification,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantQualificationDetailsDTO> getById(@PathVariable Integer id){
        ApplicantQualificationDetailsDTO applicantQualification= applicantQualificationService.findById(id);
        return new ResponseEntity<>(applicantQualification,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicantQualificationDetailsDTO> update(@PathVariable Integer id,@RequestBody ApplicantQualificationCreateUpdateDTO applicantQualification){
        ApplicantQualificationDetailsDTO updatedApplicantQualification= applicantQualificationService.update(id,applicantQualification);
        return new ResponseEntity<>(updatedApplicantQualification,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        applicantQualificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
