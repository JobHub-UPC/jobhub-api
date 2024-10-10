package com.workconnect.api;

import com.workconnect.dto.AuthResponseDTO;
import com.workconnect.dto.LoginDTO;
import com.workconnect.dto.UserProfileDTO;
import com.workconnect.dto.UserResgistrationDTO;
import com.workconnect.model.entity.User;
import com.workconnect.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    //Registrar Applicant
    @PostMapping("/register/applicant")
    public ResponseEntity<UserProfileDTO> registerApplicant(@Valid @RequestBody UserResgistrationDTO userResgistrationDTO) {
        UserProfileDTO userProfileDTO = userService.registerApplicant(userResgistrationDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    //Registar Empresa
    @PostMapping("/register/company")
    public ResponseEntity<UserProfileDTO> registerCompany(@Valid @RequestBody UserResgistrationDTO userResgistrationDTO) {
        UserProfileDTO userProfileDTO = userService.registerCompany(userResgistrationDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
}
