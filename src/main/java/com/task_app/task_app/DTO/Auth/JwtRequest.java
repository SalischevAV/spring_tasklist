package com.task_app.task_app.DTO.Auth;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {
    @NotNull(message = "Username must be not empty")
    String username;

    @NotNull(message = "Username must be not empty")
    String password;
}
