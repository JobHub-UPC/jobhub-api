package com.workconnect.api;

import com.workconnect.dto.CommunityDTO;
import com.workconnect.model.entity.Community;
import com.workconnect.service.AdminCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/communities")
@RequiredArgsConstructor
public class AdminCommunityController {
    private final AdminCommunityService adminCommunityService;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> listAll(){
        List<CommunityDTO> communities=adminCommunityService.getAll();
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CommunityDTO>> paginate(@PageableDefault(size = 5,sort = "name")Pageable pageable){
        Page<CommunityDTO> page=adminCommunityService.paginate(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CommunityDTO> create(@RequestBody CommunityDTO community){
        CommunityDTO createdCommunity=adminCommunityService.create(community);
        return new ResponseEntity<>(createdCommunity,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> findById(@PathVariable Integer id){
        CommunityDTO community=adminCommunityService.findById(id);
        return new ResponseEntity<>(community,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityDTO> update(@PathVariable Integer id,@RequestBody CommunityDTO community){
        CommunityDTO updatedCommunity=adminCommunityService.update(id,community);
        return new ResponseEntity<>(updatedCommunity,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminCommunityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
