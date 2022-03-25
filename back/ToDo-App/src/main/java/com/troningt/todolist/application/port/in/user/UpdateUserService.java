package com.troningt.todolist.application.port.in.user;


import com.troningt.todolist.domain.User;

public interface UpdateUserService {
    void updateUser(int id, User task);
}
