package com.workconnect.api;

import com.workconnect.dto.ApplicationReportDTO;
import com.workconnect.model.entity.Application;
import com.workconnect.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationReportDTO>> getAll() {
        List<ApplicationReportDTO> applications = applicationService.getAll();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ApplicationReportDTO>> paginate(
            @PageableDefault(size = 5, sort = "dateCreated") Pageable pageable) {
        Page<ApplicationReportDTO> page = applicationService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationReportDTO> getById(@PathVariable Integer id) {
        ApplicationReportDTO application = applicationService.findById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    /*
    @PostMapping
    public ResponseEntity<Application> create(@RequestBody Application application) {
        Application createApplication = applicationService.create(application);
        return new ResponseEntity<>(createApplication, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> update(
            @PathVariable Integer id, @RequestBody Application updatedApplication) {
        Application updated = applicationService.update(id, updatedApplication);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        applicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
