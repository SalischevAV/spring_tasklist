package com.task_app.task_app.Controller;

import com.task_app.task_app.DTO.Task.TaskDto;
import com.task_app.task_app.DTO.User.UserDto;
import com.task_app.task_app.Entity.Task.Task;
import com.task_app.task_app.Entity.User.User;
import com.task_app.task_app.Repositoty.Mapper.TaskMapper;
import com.task_app.task_app.Repositoty.Mapper.UserMapper;
import com.task_app.task_app.Service.TaskService;
import com.task_app.task_app.Service.UserService;
import com.task_app.task_app.Validation.OnCreate;
import com.task_app.task_app.Validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${apiPrefix}/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PutMapping
    public ResponseEntity<?> updateUser(@Validated(OnUpdate.class) @RequestBody UserDto userDto){
            User user = userMapper.toEntity(userDto);
            userService.update(user);
            User updatedUser = userService.getById(user.getId());
            return ResponseEntity.ok().body(userMapper.toDto(updatedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
          User user = userService.getById(id);
          return ResponseEntity.ok().body(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
            userService.delete(id);
            return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long id){
            List<Task> tasks = taskService.getAllByUserId(id);
            return  ResponseEntity.ok().body(taskMapper.toDto(tasks));
    }

    @PostMapping ("/{id}/tasks")
    public ResponseEntity<?> createTask(
            @PathVariable Long id,
            @Validated(OnCreate.class) @RequestBody TaskDto taskDto
    ){
            Task task = taskMapper.toEntity(taskDto);
            return  ResponseEntity.ok().body(taskService.create(task, id));
    }


}
