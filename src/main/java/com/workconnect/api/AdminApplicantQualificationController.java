package com.workconnect.api;

import com.workconnect.model.entity.ApplicantQualification;
import com.workconnect.service.AdminApplicantQualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/applicant-qualifications")
@RequiredArgsConstructor
public class AdminApplicantQualificationController {
    private final AdminApplicantQualificationService adminApplicantQualificationService;

    @GetMapping
    public ResponseEntity<List<ApplicantQualification>> listAll(){
        List<ApplicantQualification> applicantQualifications=adminApplicantQualificationService.getAll();
        return new ResponseEntity<>(applicantQualifications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ApplicantQualification>> paginate(@PageableDefault(size = 5,sort = "level")
        Pageable pageable){
        Page<ApplicantQualification> page=adminApplicantQualificationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ApplicantQualification> create(@RequestBody ApplicantQualification applicantQualification){
        ApplicantQualification createdApplicationQualification=adminApplicantQualificationService.create(applicantQualification);
        return new ResponseEntity<>(createdApplicationQualification,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicantQualification> getById(@PathVariable Integer id){
        ApplicantQualification applicantQualification=adminApplicantQualificationService.findById(id);
        return new ResponseEntity<>(applicantQualification,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicantQualification> update(@PathVariable Integer id,@RequestBody ApplicantQualification applicantQualification){
        ApplicantQualification updatedApplicantQualification=adminApplicantQualificationService.update(id,applicantQualification);
        return new ResponseEntity<>(updatedApplicantQualification,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminApplicantQualificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
