package com.task_app.task_app.Controller;

import com.task_app.task_app.DTO.Task.TaskDto;
import com.task_app.task_app.Entity.Task.Task;
import com.task_app.task_app.Repositoty.Mapper.TaskMapper;
import com.task_app.task_app.Service.TaskService;
import com.task_app.task_app.Validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService service;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        try {
            Task task = service.getById(id).orElse(null);
            return ResponseEntity.ok().body(taskMapper.toDto(task));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok(id);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto){
        try {
         Task task = taskMapper.toEntity(taskDto);
         service.update(task);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
