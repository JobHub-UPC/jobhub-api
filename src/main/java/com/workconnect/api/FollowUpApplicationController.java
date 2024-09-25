package com.workconnect.api;

import com.workconnect.model.entity.FollowUpApplication;
import com.workconnect.service.FollowUpApplicationService;
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
public class FollowUpApplicationController {
    private final FollowUpApplicationService followUpApplicationService;

    @GetMapping
    public ResponseEntity<List<FollowUpApplication>> getAll(){
        List<FollowUpApplication> followUpApplications= followUpApplicationService.getAll();
        return new ResponseEntity<>(followUpApplications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FollowUpApplication>> paginate(@PageableDefault(size = 5,sort = "last_update")Pageable pageable){
        Page<FollowUpApplication> page= followUpApplicationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUpApplication> findById(@PathVariable Integer id){
        FollowUpApplication followUpApplication= followUpApplicationService.findById(id);
        return new ResponseEntity<>(followUpApplication,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FollowUpApplication> create(@RequestBody FollowUpApplication followUpApplication){
        FollowUpApplication createdFollowUpApplication= followUpApplicationService.create(followUpApplication);
        return new ResponseEntity<>(createdFollowUpApplication,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUpApplication> update(@PathVariable Integer id, @RequestBody FollowUpApplication followUpApplication){
        FollowUpApplication updatedFollowUpApplication= followUpApplicationService.update(id,followUpApplication);
        return new ResponseEntity<>(updatedFollowUpApplication,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        followUpApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
