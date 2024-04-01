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
            Task task = service.getById(id);
            return ResponseEntity.ok().body(taskMapper.toDto(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto){
         Task task = taskMapper.toEntity(taskDto);
            return ResponseEntity.ok().body(service.update(task));
    }
}
