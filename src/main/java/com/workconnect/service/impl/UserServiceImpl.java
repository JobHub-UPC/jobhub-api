package com.workconnect.service.impl;

import com.workconnect.dto.AuthResponseDTO;
import com.workconnect.dto.LoginDTO;
import com.workconnect.dto.UserProfileDTO;
import com.workconnect.dto.UserResgistrationDTO;
import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.exception.RoleNotFoundException;
import com.workconnect.mapper.UserMapper;
import com.workconnect.model.entity.Applicant;
import com.workconnect.model.entity.Company;
import com.workconnect.model.entity.Role;
import com.workconnect.model.entity.User;
import com.workconnect.model.enums.ERole;
import com.workconnect.repository.*;
import com.workconnect.security.TokenProvider;
import com.workconnect.security.UserPrincipal;
import com.workconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Transactional
    @Override
    public UserProfileDTO registerCompany(UserResgistrationDTO userResgistrationDTO) {
        return registerUserWithRole(userResgistrationDTO, ERole.Company);
    }
    @Transactional
    @Override
    public UserProfileDTO registerApplicant(UserResgistrationDTO userResgistrationDTO) {
        return registerUserWithRole(userResgistrationDTO, ERole.Applicant);
    }

    @Transactional
    @Override
    public UserProfileDTO updateUser(Integer id, UserProfileDTO userProfileDTO) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        boolean existAsApplicant=applicantRepository.existsByPhone(userProfileDTO.getPhone());
        boolean existAsCompany=companyRepository.existsByPhone(userProfileDTO.getPhone());
        if(existAsCompany || existAsApplicant){
            throw new IllegalArgumentException("Phone number already in use");
        }
        if (user.getCompany()!=null){
            user.getCompany().setPhone(userProfileDTO.getPhone());
            user.getCompany().setCountry(userProfileDTO.getCountry());
            user.getCompany().setWebsite(userProfileDTO.getWebsite());
        }
        if (user.getApplicant()!=null){
            user.getApplicant().setPhone(userProfileDTO.getPhone());
            user.getApplicant().setCountry(userProfileDTO.getCountry());
            user.getApplicant().setCollege(userProfileDTO.getCollege());
        }
        user.setActive(true);
        User savedUser=userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    @Transactional(readOnly = true)
    @Override
    public UserProfileDTO findById(Integer id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication=  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
        User user=userPrincipal.getUser();
        String token=tokenProvider.createAccessToken(authentication);
        AuthResponseDTO authResponseDTO=userMapper.toAuthResponseDTO(user,token);
        return authResponseDTO;
    }

    private UserProfileDTO registerUserWithRole(UserResgistrationDTO userResgistrationDTO, ERole roleEnum){
        //Verify if the email is already in use or if the user is already registered
        boolean existsByEmail=userRepository.existsByEmail(userResgistrationDTO.getEmail());
        boolean existsByApplicant=applicantRepository.existsByPhone(userResgistrationDTO.getPhone());
        boolean existsByCompany=companyRepository.existsByPhone(userResgistrationDTO.getPhone());
        if(existsByEmail){
            throw new IllegalArgumentException("Email already in use");
        }
        if (existsByApplicant || existsByCompany){
            throw new IllegalArgumentException("User already registered");
        }
        Role role=roleRepository.findByName(roleEnum)
                .orElseThrow(()->new RoleNotFoundException("Role not found"));

        userResgistrationDTO.setPassword(passwordEncoder.encode(userResgistrationDTO.getPassword()));

        User user=userMapper.toEntity(userResgistrationDTO);
        user.setRole(role);

        if (roleEnum==ERole.Applicant) {
            Applicant applicant=new Applicant();
            applicant.setFirstName("");
            applicant.setLastName("");
            applicant.setDegree("");
            applicant.setCollege(userResgistrationDTO.getCollege());
            applicant.setEmail(userResgistrationDTO.getEmail());
            applicant.setPhone(userResgistrationDTO.getPhone());
            applicant.setDescription("");
            applicant.setCountry(userResgistrationDTO.getCountry());
            applicant.setUser(user);
            user.setApplicant(applicant);
        }else if (roleEnum==ERole.Company){
            Company company=new Company();
            company.setName("");
            company.setCountry(userResgistrationDTO.getCountry());
            company.setWebsite(userResgistrationDTO.getWebsite());
            company.setEmail(userResgistrationDTO.getEmail());
            company.setPhone(userResgistrationDTO.getPhone());
            company.setDescription("");
            company.setUser(user);
            user.setCompany(company);
        }
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        User savedUser=userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
}
