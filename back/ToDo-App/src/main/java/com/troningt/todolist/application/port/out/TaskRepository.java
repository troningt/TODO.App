package com.troningt.todolist.application.port.out;
import com.troningt.todolist.domain.Task;

import java.util.List;

public interface TaskRepository {
    void createTask(Task task);

    Task getTask(int id);

    List<Task> getTaskByDescription(String description);

    void updateTask(int id, Task task);

    boolean deleteTask(int id);

    void deleteAllTask();

    List<Task> getTasks();


}
