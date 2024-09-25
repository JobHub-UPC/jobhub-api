package com.workconnect.api;

import com.workconnect.dto.CompanyDTO;
import com.workconnect.model.entity.Company;
import com.workconnect.repository.CompanyRepository;
import com.workconnect.service.AdminCompanyService;
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
    public ResponseEntity<List<CompanyDTO>> listAll(){
        List<CompanyDTO> companies = adminCompanyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CompanyDTO>> paginate(@PageableDefault(size = 5, sort = "name") Pageable pageable){
        Page<CompanyDTO> page = adminCompanyService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO companyDTO){
        CompanyDTO createdCompany = adminCompanyService.create(companyDTO);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById(@PathVariable Integer id){
        CompanyDTO company = adminCompanyService.findById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO){
        CompanyDTO updatedCompany = adminCompanyService.update(id, companyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id){
        adminCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
