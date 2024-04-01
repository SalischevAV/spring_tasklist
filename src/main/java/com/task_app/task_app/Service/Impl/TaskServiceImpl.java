package com.task_app.task_app.Service.Impl;

import com.task_app.task_app.Entity.Exception.ResourceNotFoundException;
import com.task_app.task_app.Entity.Task.Status;
import com.task_app.task_app.Entity.Task.Task;
import com.task_app.task_app.Repositoty.TaskRepository;
import com.task_app.task_app.Repositoty.UserRepository;
import com.task_app.task_app.Service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long taskId) {

        return taskRepository
                        .findById(taskId)
                        .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }


    @Override
    @Transactional
    public Task update(Task task) {
        if(task.getStatus() == null){
            task.setStatus(Status.TODO);
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.TODO);
        taskRepository.create(task);
        taskRepository.assignToUserById(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long taskId) {
        taskRepository.delete(taskId);
    }
}
