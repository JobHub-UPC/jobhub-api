package com.workconnect.api;

import com.workconnect.dto.CommentsApplicationCreateUpdateDTO;
import com.workconnect.dto.CommentsApplicationDetailsDTO;
import com.workconnect.model.entity.CommentsApplication;
import com.workconnect.service.CommentsApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comments-applications")
@RequiredArgsConstructor
public class CommentsApplicationController {
    private final CommentsApplicationService commentsApplicationService;

    @GetMapping
    public ResponseEntity<List<CommentsApplicationDetailsDTO>> listAll(){
        List<CommentsApplicationDetailsDTO> commentsApplications= commentsApplicationService.getAll();
        return new ResponseEntity<>(commentsApplications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CommentsApplicationDetailsDTO>> paginate(@PageableDefault(size = 5,sort = "created") Pageable pageable){
        Page<CommentsApplicationDetailsDTO> page= commentsApplicationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsApplicationDetailsDTO> findById(@PathVariable Integer id){
        CommentsApplicationDetailsDTO commentsApplication= commentsApplicationService.findById(id);
        return new ResponseEntity<>(commentsApplication,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<CommentsApplicationDetailsDTO> create(@RequestBody CommentsApplicationCreateUpdateDTO commentsApplication){
        CommentsApplicationDetailsDTO createdCommentsApplication= commentsApplicationService.create(commentsApplication);
        return new ResponseEntity<>(createdCommentsApplication,HttpStatus.CREATED);}

    @PutMapping("/{id}")
    public ResponseEntity<CommentsApplicationDetailsDTO> update(@PathVariable Integer id,@RequestBody CommentsApplicationCreateUpdateDTO commentsApplication){
        CommentsApplicationDetailsDTO updatedCommentsApplication= commentsApplicationService.update(id,commentsApplication);
        return new ResponseEntity<>(updatedCommentsApplication,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        commentsApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
