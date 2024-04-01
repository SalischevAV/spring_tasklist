package com.task_app.task_app.Controller;

import com.task_app.task_app.DTO.Auth.JwtRequest;
import com.task_app.task_app.DTO.Auth.JwtResponse;
import com.task_app.task_app.DTO.User.UserDto;
import com.task_app.task_app.Entity.User.User;
import com.task_app.task_app.Repositoty.Mapper.UserMapper;
import com.task_app.task_app.Service.AuthService;
import com.task_app.task_app.Service.UserService;
import com.task_app.task_app.Validation.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${apiPrefix}/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private  final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest){
            return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto){
            User user = userMapper.toEntity(userDto);
            User created = userService.create(user);
            return userMapper.toDto(created);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken){
            return ResponseEntity.ok().body(authService.refresh(refreshToken));
    }
}
