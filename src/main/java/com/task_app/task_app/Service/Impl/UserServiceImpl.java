package com.task_app.task_app.Service.Impl;

import com.task_app.task_app.Entity.Exception.ResourceNotFoundException;
import com.task_app.task_app.Entity.User.Role;
import com.task_app.task_app.Entity.User.User;
import com.task_app.task_app.Repositoty.UserRepository;
import com.task_app.task_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {

        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.update(user);
    }

    @Override
    @Transactional
    public User create(User user) {
       if(userRepository.findByUsername(user.getUsername()).isPresent()){
           throw new IllegalStateException("User already exists");
       }
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            throw new IllegalStateException("Password and password confirmation do not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.create(user);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        userRepository.insertUserRole(user.getId(), Role.ROLE_USER);
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional
    public void insertUserRole(Long userId, Role role) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskOwner(Long userId, Long taskId) {
        return userRepository.isTaskOwner(userId, taskId);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}
