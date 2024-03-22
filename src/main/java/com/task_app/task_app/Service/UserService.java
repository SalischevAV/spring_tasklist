package com.task_app.task_app.Service;

import com.task_app.task_app.Entity.User.User;

public interface UserService {
    User getById(Long id);
    User getByUserName(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(Long userId, Long taskId);

    void delete (Long id);
}