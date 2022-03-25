package com.troningt.todolist.adapters.controller.dto.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Response {
    private static final long UID = 1L;
    private String message;
    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    private Object data;

    public Response(Object data) {
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }

    public Response (String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
