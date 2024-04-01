package com.task_app.task_app.Repositoty.Impl;

import com.task_app.task_app.Entity.Exception.ResourceMappingException;
import com.task_app.task_app.Entity.Task.Task;
import com.task_app.task_app.Repositoty.DataSourceConfig;
import com.task_app.task_app.Repositoty.Mapper.TaskMapper;
import com.task_app.task_app.Repositoty.Mapper.TaskRowMapper;
import com.task_app.task_app.Repositoty.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SELECT
                t.id as task_id,
                t.description as task_description,
                t.title as task_title,
                t.expiration_date as task_expiration_date,
                t.status as task_status
            FROM tasks t
            WHERE id=?
            """;
    private final String FIND_ALL_BY_USER_ID = """
            SELECT
                t.id as task_id,
                t.description as task_description,
                t.title as task_title,
                t.expiration_date as task_expiration_date,
                t.status as task_status
            FROM tasks t
                JOIN users_tasks ut on t.id = ut.task_id
            WHERE id=?
            """;
    private final String ASSIGN = """
            INSERT INTO users_tasks (task_id, user_id)
            VALUES (?, ?)
            """;

    private final String DELETE = """
            DELETE FROM tasks
            WHERE id=?
            """;

    private final String UPDATE = """
            UPDATE tasks
            SET
               title = ?,
               description = ?,
               expiration_date = ?,
               status = ?
            WHERE id= ?
            """;
    private final String CREATE = """
            INSERT INTO tasks (title, description, expiration_date, status)
            VALUES (?, ?, ?, ?)
            """;

    @Override
    public Optional<Task> findById(Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, taskId);
            try(ResultSet rs = statement.executeQuery()){
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while searching task by Id");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try(ResultSet rs = statement.executeQuery()){
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while finding all users tasks");
        }
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while assigning task to user");
        }
    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, task.getTitle());

            if(task.getDescription() == null){
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }

            statement.setString(4, task.getStatus().name());

            if(task.getExpirationDate() == null){
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }

            statement.setLong(5, task.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while updating task");
        }
    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    CREATE,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, task.getTitle());

            if(task.getDescription() == null){
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }

            statement.setString(4, task.getStatus().name());

            if(task.getExpirationDate() == null){
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }

            statement.executeUpdate();

            try(ResultSet rs = statement.getGeneratedKeys()){
                rs.next();
                task.setId(rs.getLong(1));
            }


        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while creating task");
        }
    }

    @Override
    public void delete(Long taskId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("SQL Error while deleting task");
        }
    }
}
