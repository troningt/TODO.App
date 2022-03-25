package com.troningt.todolist.application.port.in.task;

import com.troningt.todolist.domain.Task;

public interface UpdateTaskService {
    void updateTask(int id, Task task);
}
