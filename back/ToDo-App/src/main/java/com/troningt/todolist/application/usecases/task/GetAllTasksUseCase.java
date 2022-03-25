package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.GetAllTasksService;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTasksUseCase implements GetAllTasksService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }
}