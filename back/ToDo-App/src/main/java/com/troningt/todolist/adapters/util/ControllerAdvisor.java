package com.troningt.todolist.adapters.util;

import com.troningt.todolist.adapters.controller.dto.model.Response;
import com.troningt.todolist.config.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler({TaskCustomErrorException.class,})
    public ResponseEntity<Response> handleTaskCustomErrorException(
            TaskCustomErrorException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Response> handleTaskNotFoundException(
            TaskNotFoundException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserCustomErrorException.class,})
    public ResponseEntity<Response> handleUserCustomErrorException(
            UserCustomErrorException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Response> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Response> handleNoDataFoundException(
            NoDataFoundException ex, WebRequest request) {

        Response response = new Response("No Tasks found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
