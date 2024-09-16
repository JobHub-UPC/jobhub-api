package com.workconnect.api;

import com.workconnect.model.entity.CommentsApplication;
import com.workconnect.service.AdminCommentsApplicationService;
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
public class AdminCommentsApplicationController {
    private final AdminCommentsApplicationService adminCommentsApplicationService;

    @GetMapping
    public ResponseEntity<List<CommentsApplication>> listAll(){
        List<CommentsApplication> commentsApplications=adminCommentsApplicationService.getAll();
        return new ResponseEntity<>(commentsApplications, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CommentsApplication>> paginate(@PageableDefault(size = 5,sort = "created") Pageable pageable){
        Page<CommentsApplication> page=adminCommentsApplicationService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsApplication> findById(@PathVariable Integer id){
        CommentsApplication commentsApplication=adminCommentsApplicationService.findById(id);
        return new ResponseEntity<>(commentsApplication,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<CommentsApplication> create(@RequestBody CommentsApplication commentsApplication){
        CommentsApplication createdCommentsApplication=adminCommentsApplicationService.create(commentsApplication);
        return new ResponseEntity<>(createdCommentsApplication,HttpStatus.CREATED);}

    @PutMapping("{/id}")
    public ResponseEntity<CommentsApplication> update(@PathVariable Integer id,@RequestBody CommentsApplication commentsApplication){
        CommentsApplication updatedCommentsApplication=adminCommentsApplicationService.update(id,commentsApplication);
        return new ResponseEntity<>(updatedCommentsApplication,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminCommentsApplicationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
