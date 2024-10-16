package com.workconnect.service.impl;

import com.workconnect.dto.CompanyCreateDTO;
import com.workconnect.dto.CompanyUpdateDTO;
import com.workconnect.dto.CompanyDetailsDTO;
import com.workconnect.exception.BadRequestException;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.mapper.CompanyMapper;
import com.workconnect.model.entity.Company;
import com.workconnect.model.entity.Role;
import com.workconnect.model.entity.User;
import com.workconnect.model.enums.ERole;
import com.workconnect.repository.CompanyRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.AdminCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminCompanyServiceImpl implements AdminCompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyMapper companyMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CompanyDetailsDTO> getAll() {
        //List<Company> companies = companyRepository.findAll();
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDetailsDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CompanyDetailsDTO> paginate(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(companyMapper::toDetailsDto);
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyDetailsDTO findById(Integer id) {
        Company company =  companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return companyMapper.toDetailsDto(company);
    }

    @Transactional
    @Override
    public CompanyDetailsDTO create(CompanyCreateDTO companyCreateDTO) {
        // Verifica si la empresa ya existe
        companyRepository.findByNameAndEmail(companyCreateDTO.getName(), companyCreateDTO.getEmail())
                .ifPresent(existingCompany -> {
                    throw new BadRequestException("Company already exists");
                });

        // Crea un nuevo usuario
        User user = new User(); // Asegúrate de que la clase User tenga un constructor vacío
        //user.setRole("Company"); // Aquí se setea el rol a "company"
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        user.setEmail(companyCreateDTO.getEmail());
        user.setPassword(companyCreateDTO.getPassword());

        Role companyRole = new Role();
        companyRole.setName(ERole.Company);
        user.setRole(companyRole);


        // Guarda el nuevo usuario
        user = userRepository.save(user); // Guarda el usuario en la base de datos

        // Mapea la empresa y la asocia con el nuevo usuario
        Company company = companyMapper.toEntity(companyCreateDTO);
        company.setUser(user);

        // Guarda la nueva empresa
        company = companyRepository.save(company);

        return companyMapper.toDetailsDto(company);
    }


    @Transactional
    @Override
    public CompanyDetailsDTO update(Integer id, CompanyUpdateDTO updateCompanyDTO) {
        Company companyFromDb = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));


        companyRepository.findByNameAndEmail(updateCompanyDTO.getName(), updateCompanyDTO.getEmail())
                .filter(existingCompany -> !existingCompany.getId().equals(id))
                .ifPresent(existingCompany -> {
                    throw new BadRequestException("Company already exists");
                });

        companyFromDb.setName(updateCompanyDTO.getName());
        companyFromDb.setDescription(updateCompanyDTO.getDescription());
        companyFromDb.setCountry(updateCompanyDTO.getCountry());
        companyFromDb.setWebsite(updateCompanyDTO.getWebsite());
        companyFromDb.setEmail(updateCompanyDTO.getEmail());
        companyFromDb.setPhone(updateCompanyDTO.getPhone());

        // Obtener el usuario asociado a la empresa
        User user = companyFromDb.getUser();
        if (user != null) {
            // Actualiza el correo electrónico del usuario
            user.setEmail(updateCompanyDTO.getEmail());
            //userRepository.save(user);
        }
        companyFromDb = companyRepository.save(companyFromDb);
        return companyMapper.toDetailsDto(companyFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Company company = companyRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        companyRepository.delete(company);
    }
}
