package com.workconnect.service;

import com.workconnect.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserService {
    List<User> getAll();
    Page<User> paginate(Pageable pageable);
    User findById(Integer id);
    User create(User user);
    User update(Integer id, User updatedUser);
    void delete(Integer id);

}
