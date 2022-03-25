package com.troningt.todolist.application.usecases.task;

import com.troningt.todolist.application.port.in.task.DeleteTaskService;
import com.troningt.todolist.application.port.out.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteTaskUseCase implements DeleteTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}