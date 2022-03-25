package com.troningt.todolist.application.port.in.user;

import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;

import java.util.List;

public interface UserService {
    void createUser(UserEntity userEntity);

    UserEntity getUser(int userId);

    void updateProfile(String username, String email, int userId);

    void updateUser(UserEntity userEntity);

    List<UserEntity> getTasksByUser(int userId);
}
