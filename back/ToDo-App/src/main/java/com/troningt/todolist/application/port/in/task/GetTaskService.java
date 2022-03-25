package com.troningt.todolist.application.port.in.task;
import com.troningt.todolist.domain.Task;

public interface GetTaskService {
    Task getTask(int id);
}
