package com.task_app.task_app.Service;

import com.task_app.task_app.Entity.User.Role;
import com.task_app.task_app.Entity.User.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(Long id);
    Optional<User> getByUsername(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long userId);
}
