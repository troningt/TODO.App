package com.troningt.todolist.application.port.in.task;

import java.util.List;

import com.troningt.todolist.domain.Task;

public interface GetAllTasksService {
    List<Task> getTasks();
}
