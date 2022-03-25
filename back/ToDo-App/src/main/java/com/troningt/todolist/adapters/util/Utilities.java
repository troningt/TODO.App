package com.troningt.todolist.adapters.util;

import com.troningt.todolist.adapters.controller.dto.model.Response;
import org.springframework.stereotype.Component;

@Component
public class Utilities<T> {
    public Response generateResponse(String message, T data) {
        return new Response(message, data);
    }

    public Response generateResponse(T data) {
        return new Response(data);
    }
}
