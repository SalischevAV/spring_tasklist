package com.task_app.task_app.Repositoty;

import com.task_app.task_app.Entity.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);

    void assignToUserById(Long taskId, Long userId);

}
