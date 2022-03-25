package com.troningt.todolist.application.port.in.task;

import com.troningt.todolist.config.exception.TaskCustomErrorException;
import com.troningt.todolist.domain.Task;

public interface CreateTaskService {
    void createTask(Task task);
}
