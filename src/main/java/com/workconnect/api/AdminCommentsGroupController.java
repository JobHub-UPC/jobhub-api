package com.workconnect.api;

import com.workconnect.model.entity.CommentsGroup;
import com.workconnect.service.AdminCommentsGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comments-group")
@RequiredArgsConstructor
public class AdminCommentsGroupController {
    private final AdminCommentsGroupService adminCommentsGroupService;

    @GetMapping
    public ResponseEntity<List<CommentsGroup>> getAll(){
        List<CommentsGroup> commentsGroups= adminCommentsGroupService.getAll();
        return new ResponseEntity<>(commentsGroups, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CommentsGroup>> paginate(@PageableDefault(size = 5, sort = "likes_count")Pageable pageable){
        Page<CommentsGroup> commentsGroups=adminCommentsGroupService.paginate(pageable);
        return new ResponseEntity<>(commentsGroups,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsGroup> findById(@PathVariable Integer id){
        CommentsGroup commentsGroup=adminCommentsGroupService.findById(id);
        return new ResponseEntity<>(commentsGroup,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentsGroup> create(@RequestBody CommentsGroup commentsGroup){
        CommentsGroup createdCommentsGroup=adminCommentsGroupService.create(commentsGroup);
        return new ResponseEntity<>(createdCommentsGroup,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentsGroup> update(@PathVariable Integer id,@RequestBody CommentsGroup commentsGroup){
        CommentsGroup updatedCommentsGroup=adminCommentsGroupService.update(id,commentsGroup);
        return new ResponseEntity<>(updatedCommentsGroup,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminCommentsGroupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
