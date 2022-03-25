package com.troningt.todolist.application.port.out;

import com.troningt.todolist.domain.User;

import java.util.List;

public interface UserRepository {
    void createUser(User task);

    User getUser(int id);

    User getUserByEmail(String email);

    List<User> getUsers();

    void updateUser(int id, User user);

    boolean deleteUser(int id);

    void deleteAllUsers();
}