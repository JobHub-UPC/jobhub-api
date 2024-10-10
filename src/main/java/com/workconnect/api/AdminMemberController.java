package com.workconnect.api;

import com.workconnect.dto.MemberDTO;
import com.workconnect.model.entity.Member;
import com.workconnect.service.AdminMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/members")
@RequiredArgsConstructor
@PreAuthorize("hasRole('Applicant')")
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> listAll() {
        List<MemberDTO> members = adminMemberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<MemberDTO>> paginate(@PageableDefault(size = 5, sort = "id")Pageable pageable) {
        Page<MemberDTO> page = adminMemberService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody MemberDTO memberDTO) {
        MemberDTO createMember = adminMemberService.create(memberDTO);
        return new ResponseEntity<>(createMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Integer id) {
        MemberDTO member = adminMemberService.findById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updateMember = adminMemberService.update(id, memberDTO);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        adminMemberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
