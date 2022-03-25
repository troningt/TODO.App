package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.GetTaskService;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskUseCase implements GetTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task getTask(int id) {
        return taskRepository.getTask(id);
    }
}