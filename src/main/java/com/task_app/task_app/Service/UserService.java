package com.task_app.task_app.Service;

import com.task_app.task_app.Entity.User.Role;
import com.task_app.task_app.Entity.User.User;

import java.util.Optional;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);

    void update(User user);

    User create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long userId);
}
