package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.UpdateTaskService;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateTaskUseCase implements UpdateTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void updateTask(int id, Task task) {
        taskRepository.updateTask(id, task);
    }
}