package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.GetTaskByDescriptionService;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTaskByDescriptionUseCase implements GetTaskByDescriptionService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getTaskByDescription(String description) {
        return taskRepository.getTaskByDescription(description);
    }
}
