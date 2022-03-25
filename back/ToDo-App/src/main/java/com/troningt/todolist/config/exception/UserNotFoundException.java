package com.troningt.todolist.config.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super(String.format("User with Id %d not found", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email %s not found", email));
    }
}