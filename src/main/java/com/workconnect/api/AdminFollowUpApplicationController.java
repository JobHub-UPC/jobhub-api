package com.workconnect.api;

import com.workconnect.model.entity.FollowUpApplication;
import com.workconnect.service.AdminFollowUpApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/follow-up-application")
@RequiredArgsConstructor
public class AdminFollowUpApplicationController {
    private final AdminFollowUpApplicationService adminFollowUpApplicationService;

    @GetMapping
    public ResponseEntity<List<FollowUpApplication>> getAll(){
        List<FollowUpApplication> followUpApplications=adminFollowUpApplicationService.getAll();
        return new ResponseEntity<>(followUpApplications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FollowUpApplication>> paginate(@PageableDefault(size = 5,sort = "last_update")Pageable pageable){
        Page<FollowUpApplication> page=adminFollowUpApplicationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUpApplication> findById(@PathVariable Integer id){
        FollowUpApplication followUpApplication=adminFollowUpApplicationService.findById(id);
        return new ResponseEntity<>(followUpApplication,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FollowUpApplication> create(@RequestBody FollowUpApplication followUpApplication){
        FollowUpApplication createdFollowUpApplication=adminFollowUpApplicationService.create(followUpApplication);
        return new ResponseEntity<>(createdFollowUpApplication,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUpApplication> update(@PathVariable Integer id, @RequestBody FollowUpApplication followUpApplication){
        FollowUpApplication updatedFollowUpApplication=adminFollowUpApplicationService.update(id,followUpApplication);
        return new ResponseEntity<>(updatedFollowUpApplication,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminFollowUpApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
