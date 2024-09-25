package com.workconnect.service;

import com.workconnect.dto.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCompanyService  {
    List<CompanyDTO> getAll();
    Page<CompanyDTO> paginate(Pageable pageable);
    CompanyDTO findById(Integer id);
    CompanyDTO create(CompanyDTO companyDTO);
    CompanyDTO update(Integer id, CompanyDTO updateCompanyDTO);
    void delete(Integer id);

}
