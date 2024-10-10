package com.workconnect.service.impl;

import com.workconnect.repository.PasswordResetTokenRepository;
import com.workconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
}
