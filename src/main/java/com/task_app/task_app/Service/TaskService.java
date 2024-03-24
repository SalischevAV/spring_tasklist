package com.task_app.task_app.Service;

import com.task_app.task_app.Entity.Task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getById(Long taskId);
    List<Task> getAllByUserId(Long userId);

    void assignToUserById(Long taskId, Long userId);

    void update(Task task);

    void create(Task task, Long userId);

    void delete(Long taskId);
}
