package com.workconnect.api;

import com.workconnect.dto.FollowUpApplicationCreateUpdateDTO;
import com.workconnect.dto.FollowUpApplicationDetailsDTO;
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
@RequestMapping("/follow-up-application")
@RequiredArgsConstructor
public class FollowUpApplicationController {
    private final FollowUpApplicationService followUpApplicationService;

    @GetMapping
    public ResponseEntity<List<FollowUpApplicationDetailsDTO>> getAll(){
        List<FollowUpApplicationDetailsDTO> followUpApplications= followUpApplicationService.getAll();
        return new ResponseEntity<>(followUpApplications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FollowUpApplicationDetailsDTO>> paginate(@PageableDefault(size = 5,sort = "id")Pageable pageable){
        Page<FollowUpApplicationDetailsDTO> page= followUpApplicationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUpApplicationDetailsDTO> findById(@PathVariable Integer id){
        FollowUpApplicationDetailsDTO followUpApplication= followUpApplicationService.findById(id);
        return new ResponseEntity<>(followUpApplication,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FollowUpApplicationDetailsDTO> create(@RequestBody FollowUpApplicationCreateUpdateDTO followUpApplication){
        FollowUpApplicationDetailsDTO createdFollowUpApplication= followUpApplicationService.create(followUpApplication);
        return new ResponseEntity<>(createdFollowUpApplication,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUpApplicationDetailsDTO> update(@PathVariable Integer id, @RequestBody FollowUpApplicationCreateUpdateDTO followUpApplication){
        FollowUpApplicationDetailsDTO updatedFollowUpApplication= followUpApplicationService.update(id,followUpApplication);
        return new ResponseEntity<>(updatedFollowUpApplication,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        followUpApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
