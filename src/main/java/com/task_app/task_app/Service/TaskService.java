package com.task_app.task_app.Service;

import com.task_app.task_app.Entity.Task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task getById(Long taskId);
    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task, Long userId);

    void delete(Long taskId);
}
