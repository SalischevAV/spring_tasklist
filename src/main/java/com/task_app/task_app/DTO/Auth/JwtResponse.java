package com.task_app.task_app.DTO.Auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {
    Long id;
    String username;
    String accessToken;
    String refreshToken;
}
