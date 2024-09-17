package com.workconnect.api;

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
    public ResponseEntity<List<Company>> listAll(){
        List<Company> companies = adminCompanyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Company>> paginate(@PageableDefault(size = 5, sort = "companyName") Pageable pageable){
        Page<Company> page = adminCompanyService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company){
        Company createdCompany = adminCompanyService.create(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Integer id){
        Company company = adminCompanyService.findById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Integer id, @RequestBody Company company){
        Company updatedCompany = adminCompanyService.update(id, company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id){
        adminCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
