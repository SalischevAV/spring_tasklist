package com.task_app.task_app.Service.Impl;

import com.task_app.task_app.Entity.Task.Task;
import com.task_app.task_app.Service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public Optional<Task> getById(Long taskId) {
        return Optional.empty();
    }

    @Override
    public List<Task> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task, Long userId) {

    }

    @Override
    public void delete(Long taskId) {

    }
}
