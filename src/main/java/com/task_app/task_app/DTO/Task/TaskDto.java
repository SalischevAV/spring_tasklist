package com.task_app.task_app.DTO.Task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task_app.task_app.Entity.Task.Status;
import com.task_app.task_app.Validation.OnCreate;
import com.task_app.task_app.Validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TaskDto {
        @NotNull(message = "Id must be not empty", groups = OnUpdate.class)
        Long id;

        @NotNull(message = "Title must be not empty", groups = {OnUpdate.class, OnCreate.class})
        @Length(max = 255, message = "Length must be less than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
        String title;

        @Length(max = 255, message = "Length must be less than 255 symbols", groups = {OnUpdate.class, OnCreate.class})
        String description;

        Status status;

        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime expirationDate;
    }

