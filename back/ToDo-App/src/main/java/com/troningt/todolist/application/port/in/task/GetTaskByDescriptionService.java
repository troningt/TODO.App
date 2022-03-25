package com.troningt.todolist.application.port.in.task;
import com.troningt.todolist.domain.Task;

import java.util.List;

public interface GetTaskByDescriptionService {
    List<Task> getTaskByDescription(String description);
}
