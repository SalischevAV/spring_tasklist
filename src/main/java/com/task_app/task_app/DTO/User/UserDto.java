package com.task_app.task_app.DTO.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task_app.task_app.Validation.OnCreate;
import com.task_app.task_app.Validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @NotNull(message = "Id must be not empty", groups = OnUpdate.class)
    Long id;

    @NotNull(message = "Name must be not empty", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Length must be less than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    String name;

    @NotNull(message = "Username must be not empty", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Length must be less than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
    String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not empty", groups = {OnUpdate.class, OnCreate.class})
    String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not empty", groups = OnCreate.class)
    String passwordConfirmation;
}
