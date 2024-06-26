package com.task_app.task_app.Repositoty;

import com.task_app.task_app.Entity.User.Role;
import com.task_app.task_app.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository{

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete(Long userId);
}
