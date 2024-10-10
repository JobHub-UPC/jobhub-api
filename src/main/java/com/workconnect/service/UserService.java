package com.workconnect.service;

import com.workconnect.dto.AuthResponseDTO;
import com.workconnect.dto.LoginDTO;
import com.workconnect.dto.UserProfileDTO;
import com.workconnect.dto.UserResgistrationDTO;

public interface UserService {
    // Register Company
    UserProfileDTO registerCompany(UserResgistrationDTO userResgistrationDTO);
    // Register Applicant
    UserProfileDTO registerApplicant(UserResgistrationDTO userResgistrationDTO);
    // Update User
    UserProfileDTO updateUser(Integer id, UserProfileDTO userProfileDTO);
    // Get User by ID
    UserProfileDTO findById(Integer id);
    // Login
    AuthResponseDTO login(LoginDTO loginDTO);
}
