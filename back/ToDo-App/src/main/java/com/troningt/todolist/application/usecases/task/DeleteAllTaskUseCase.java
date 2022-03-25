package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.DeleteAllTasksService;
import com.troningt.todolist.application.port.out.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAllTaskUseCase implements DeleteAllTasksService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAllTask();
    }
}