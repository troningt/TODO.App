package com.troningt.todolist.application.port.in.user;

import com.troningt.todolist.domain.User;

public interface CreateUserService {
    void createUser(User task);
}
