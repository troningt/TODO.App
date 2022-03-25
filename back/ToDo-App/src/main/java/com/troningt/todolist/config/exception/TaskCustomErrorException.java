package com.troningt.todolist.config.exception;

public class TaskCustomErrorException extends RuntimeException {
    public TaskCustomErrorException() {
        super();
    }

    public TaskCustomErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TaskCustomErrorException(final String message) {
        super(message);
    }

    public TaskCustomErrorException(final Throwable cause) {
        super(cause);
    }
}
