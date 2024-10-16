package com.workconnect.service.impl;

import com.workconnect.exception.ResourceNotFoundException;
import com.workconnect.integration.notification.email.dto.Mail;
import com.workconnect.integration.notification.email.service.EmailService;
import com.workconnect.model.entity.PasswordResetToken;
import com.workconnect.model.entity.User;
import com.workconnect.repository.PasswordResetTokenRepository;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final UserRepository userRepo;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public void createAndSendPasswordResetToken(String email) throws Exception {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not founded with email: " + email));

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiration(10);
        passwordResetTokenRepository.save(token);

        Map<String, Object> model = new HashMap<>();
        String resetUrl = "http://localhost:4200/#/forgot" + token.getToken();
        model.put("user", user.getEmail());
        model.put("resetUrl", resetUrl);

        Mail mail = emailService.createMail(
                user.getEmail(),
                "Restablecer Contraseña",
                model,
                mailFrom
        );

        emailService.sendMail(mail,"email/password-reset-template");

    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token not founded with"));
    }

    @Override
    public void removeResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public boolean isValidToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .filter(t -> !t.isExpired())
                .isPresent();
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .orElseThrow(() -> new ResourceNotFoundException("Token invalid or expired"));

        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);

    }

}
