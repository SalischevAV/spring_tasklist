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
    public ResponseEntity<?> login(@Validated @RequestBody JwtRequest loginRequest){
        try{
            return ResponseEntity.ok().body(authService.login(loginRequest));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(OnCreate.class) @RequestBody UserDto userDto){
        try{
            User user = userMapper.toEntity(userDto);
            User created = userService.create(user);
            return ResponseEntity.ok().body(userMapper.toDto(created));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken){
        try{
            return ResponseEntity.ok().body(authService.refresh(refreshToken));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
