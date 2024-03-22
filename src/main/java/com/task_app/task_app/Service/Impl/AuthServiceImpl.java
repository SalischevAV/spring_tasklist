package com.task_app.task_app.Service.Impl;

import com.task_app.task_app.DTO.Auth.JwtRequest;
import com.task_app.task_app.DTO.Auth.JwtResponse;
import com.task_app.task_app.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
