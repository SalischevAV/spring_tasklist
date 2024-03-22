package com.task_app.task_app.Entity.User;

import com.task_app.task_app.Entity.Task.Task;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String name;
    String username;
    String password;
    String passwordConfirmation;
    Set<Role> roles;
    List<Task> tasks;
}
