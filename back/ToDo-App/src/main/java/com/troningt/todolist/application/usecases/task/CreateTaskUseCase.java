package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.CreateTaskService;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase implements CreateTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {
        taskRepository.createTask(task);
    }

}