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
        try {
            User user = userMapper.toEntity(userDto);
            userService.update(user);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
          User user = userService.getById(id);
          return ResponseEntity.ok().body(userMapper.toDto(user));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok(id);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<?> getTasksByUserId(@PathVariable Long id){
        try{
            List<Task> tasks = taskService.getAllByUserId(id);
            return  ResponseEntity.ok().body(taskMapper.toDto(tasks));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping ("/{id}/tasks")
    public ResponseEntity<?> createTask(
            @PathVariable Long id,
            @Validated(OnCreate.class) @RequestBody TaskDto taskDto
    ){
        try{
            Task task = taskMapper.toEntity(taskDto);
            return  ResponseEntity.ok().body(taskService.create(task, id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
