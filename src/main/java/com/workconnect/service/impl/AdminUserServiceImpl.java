package com.workconnect.service.impl;

import com.workconnect.model.entity.User;
import com.workconnect.repository.UserRepository;
import com.workconnect.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<User> paginate(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    @Override
    public User create(User user) {
        //user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(Integer id, User updatedUser) {
        User userFromDb = findById(id);
        userFromDb.setEmail(updatedUser.getEmail());
        userFromDb.setRole(updatedUser.getRole());
        userFromDb.setActive(updatedUser.getActive());
        userFromDb.setPassword(updatedUser.getPassword());
        userFromDb.setCreated(LocalDateTime.now());

        return userRepository.save(userFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
