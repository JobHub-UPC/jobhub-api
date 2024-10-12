package com.workconnect.mapper;

import com.workconnect.dto.AuthResponseDTO;
import com.workconnect.dto.LoginDTO;
import com.workconnect.dto.UserProfileDTO;
import com.workconnect.dto.UserResgistrationDTO;
import com.workconnect.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
    }

    public User toEntity(UserResgistrationDTO userResgistrationDTO){
    return modelMapper.map(userResgistrationDTO,User.class);
    }
    public UserProfileDTO toDTO(User user){
        UserProfileDTO userProfileDTO=modelMapper.map(user, UserProfileDTO.class);
        if(user.getCompany()!=null){
            userProfileDTO.setPhone(user.getCompany().getPhone());
            userProfileDTO.setCountry(user.getCompany().getCountry());
            userProfileDTO.setWebsite(user.getCompany().getWebsite());
            userProfileDTO.setRole(user.getRole().getName());
        }
        if (user.getApplicant()!=null){
            userProfileDTO.setPhone(user.getApplicant().getPhone());
            userProfileDTO.setCountry(user.getApplicant().getCountry());
            userProfileDTO.setCollege(user.getApplicant().getCollege());
            userProfileDTO.setRole(user.getRole().getName());
        }
        return userProfileDTO;
    }

    //Convertir LoginDTO a User
    public User toEntity(LoginDTO loginDTO){
        return modelMapper.map(loginDTO,User.class);
    }
    //Convertir de User a AuthResponseDTO
    public AuthResponseDTO toAuthResponseDTO(User user, String token){
        AuthResponseDTO authResponseDTO=modelMapper.map(user, AuthResponseDTO.class);
        authResponseDTO.setToken(token);
        //Obtener el phone
        String phone ="545";
        authResponseDTO.setPhone(phone);
        authResponseDTO.setRole(user.getRole().getName().name());
        return authResponseDTO;
    }


}
