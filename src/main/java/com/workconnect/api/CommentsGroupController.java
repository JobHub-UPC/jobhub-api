package com.workconnect.api;

import com.workconnect.dto.CommentsGroupCreateUpdateDTO;
import com.workconnect.dto.CommentsGroupDetailsDTO;
import com.workconnect.model.entity.CommentsGroup;
import com.workconnect.service.CommentsGroupService;
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
public class CommentsGroupController {
    private final CommentsGroupService commentsGroupService;

    @GetMapping
    public ResponseEntity<List<CommentsGroupDetailsDTO>> getAll(){
        List<CommentsGroupDetailsDTO> commentsGroups= commentsGroupService.getAll();
        return new ResponseEntity<>(commentsGroups, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CommentsGroupDetailsDTO>> paginate(@PageableDefault(size = 5, sort = "likes_count")Pageable pageable){
        Page<CommentsGroupDetailsDTO> commentsGroups= commentsGroupService.paginate(pageable);
        return new ResponseEntity<>(commentsGroups,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsGroupDetailsDTO> findById(@PathVariable Integer id){
        CommentsGroupDetailsDTO commentsGroup= commentsGroupService.findById(id);
        return new ResponseEntity<>(commentsGroup,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentsGroupDetailsDTO> create(@RequestBody CommentsGroupCreateUpdateDTO commentsGroup){
        CommentsGroupDetailsDTO createdCommentsGroup= commentsGroupService.create(commentsGroup);
        return new ResponseEntity<>(createdCommentsGroup,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentsGroupDetailsDTO> update(@PathVariable Integer id,@RequestBody CommentsGroupCreateUpdateDTO commentsGroup){
        CommentsGroupDetailsDTO updatedCommentsGroup= commentsGroupService.update(id,commentsGroup);
        return new ResponseEntity<>(updatedCommentsGroup,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        commentsGroupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
