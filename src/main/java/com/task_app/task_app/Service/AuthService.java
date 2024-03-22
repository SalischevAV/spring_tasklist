package com.task_app.task_app.Service;

import com.task_app.task_app.DTO.Auth.JwtRequest;
import com.task_app.task_app.DTO.Auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
