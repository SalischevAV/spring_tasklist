package com.task_app.task_app.Service.Impl;

import com.task_app.task_app.DTO.Auth.JwtRequest;
import com.task_app.task_app.DTO.Auth.JwtResponse;
import com.task_app.task_app.Entity.User.User;
import com.task_app.task_app.Security.JwtTokenProvider;
import com.task_app.task_app.Service.AuthService;
import com.task_app.task_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(
                jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles())
        );
        jwtResponse.setRefreshToken(
                jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername())
        );

        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUsersTokens(refreshToken);
    }
}
