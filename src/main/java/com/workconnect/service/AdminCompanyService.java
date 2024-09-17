package com.workconnect.service;

import com.workconnect.model.entity.Company;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;


public interface AdminCompanyService  {
    List<Company> getAll();
    Page<Company> paginate(Pageable pageable);
    Company findById(Integer id);
    Company create(Company company);
    Company update(Integer id, Company updateCompany);
    void delete(Integer id);

}
