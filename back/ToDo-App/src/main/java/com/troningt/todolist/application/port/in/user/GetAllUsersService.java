package com.troningt.todolist.application.port.in.user;



import com.troningt.todolist.domain.User;

import java.util.List;

public interface GetAllUsersService {
    List<User> getUsers();
}
