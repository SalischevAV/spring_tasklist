package com.task_app.task_app.Repositoty.Mapper;

import com.task_app.task_app.DTO.Task.TaskDto;
import com.task_app.task_app.Entity.Task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    List<TaskDto> toDto(List<Task> tasks);

    Task toEntity(TaskDto taskDto);
}
