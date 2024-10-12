package com.workconnect.api;

import com.workconnect.dto.CompanyCreateDTO;
import com.workconnect.dto.CompanyUpdateDTO;
import com.workconnect.dto.CompanyDetailsDTO;
import com.workconnect.service.AdminCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final AdminCompanyService adminCompanyService;

    @GetMapping
    public ResponseEntity<List<CompanyDetailsDTO>> listAll(){
        List<CompanyDetailsDTO> companies = adminCompanyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CompanyDetailsDTO>> paginate(@PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<CompanyDetailsDTO> page = adminCompanyService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDetailsDTO> create(@Valid @RequestBody CompanyCreateDTO companyDTO){
        CompanyDetailsDTO createdCompany = adminCompanyService.create(companyDTO);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDetailsDTO> getById(@PathVariable Integer id){
        CompanyDetailsDTO company = adminCompanyService.findById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDetailsDTO> updateCompany(@PathVariable Integer id, @RequestBody CompanyUpdateDTO companyDTO){
        CompanyDetailsDTO updatedCompany = adminCompanyService.update(id, companyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id){
        adminCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
