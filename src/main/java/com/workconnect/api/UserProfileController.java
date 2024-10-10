package com.workconnect.api;

import com.workconnect.dto.UserProfileDTO;
import com.workconnect.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {
    private final UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Integer id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedUserProfileDTO = userService.updateUser(id, userProfileDTO);
        return new ResponseEntity<>(updatedUserProfileDTO,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer id) {
        UserProfileDTO userProfileDTO = userService.findById(id);
        return new ResponseEntity<>(userProfileDTO,HttpStatus.OK);
    }
}
