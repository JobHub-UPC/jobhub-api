package com.workconnect.api;

import com.workconnect.model.entity.Member;
import com.workconnect.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    @GetMapping
    public ResponseEntity<List<Member>> listAll() {
        List<Member> members = adminMemberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Member>> paginate(@PageableDefault(size = 5, sort = "id")Pageable pageable) {
        Page<Member> page = adminMemberService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member createMember = adminMemberService.create(member);
        return new ResponseEntity<>(createMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Integer id) {
        Member member = adminMemberService.findById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        Member updateMember = adminMemberService.update(id, member);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        adminMemberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
