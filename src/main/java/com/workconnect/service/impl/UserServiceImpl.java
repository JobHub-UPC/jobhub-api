package com.workconnect.service.impl;

import com.workconnect.model.entity.User;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user) {
        if (userRepository.existByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail already exists");
        }

        user.setCreated(LocalDateTime.now());
        return userRepository.save(user);
    }
}
