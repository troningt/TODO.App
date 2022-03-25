package com.troningt.todolist.config.exception;

public class UserCustomErrorException extends RuntimeException {
    public UserCustomErrorException() {
        super();
    }

    public UserCustomErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserCustomErrorException(final String message) {
        super(message);
    }

    public UserCustomErrorException(final Throwable cause) {
        super(cause);
    }
}
