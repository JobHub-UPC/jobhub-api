package com.workconnect.service;

import com.workconnect.dto.CompanyCreateDTO;
import com.workconnect.dto.CompanyUpdateDTO;
import com.workconnect.dto.CompanyDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCompanyService  {
    List<CompanyDetailsDTO> getAll();
    Page<CompanyDetailsDTO> paginate(Pageable pageable);
    CompanyDetailsDTO findById(Integer id);
    CompanyDetailsDTO create(CompanyCreateDTO companyCreateDTO);
    CompanyDetailsDTO update(Integer id, CompanyUpdateDTO updateCompanyDTO);
    void delete(Integer id);

}
