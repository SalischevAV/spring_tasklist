package com.task_app.task_app.Repositoty.Mapper;

import com.task_app.task_app.DTO.User.UserDto;
import com.task_app.task_app.Entity.User.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
