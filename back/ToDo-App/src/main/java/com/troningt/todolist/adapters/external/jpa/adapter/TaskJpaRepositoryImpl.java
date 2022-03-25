package com.troningt.todolist.adapters.external.jpa.adapter;

import com.troningt.todolist.adapters.external.jpa.adapter.repository.ITaskJpaRepository;
import com.troningt.todolist.adapters.external.jpa.entity.TaskEntity;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.config.exception.NoDataFoundException;
import com.troningt.todolist.config.exception.TaskCustomErrorException;
import com.troningt.todolist.config.exception.TaskNotFoundException;
import com.troningt.todolist.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskJpaRepositoryImpl implements TaskRepository {
    @Autowired
    ITaskJpaRepository taskJpaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createTask(Task task) {
        try {
            taskJpaRepository.save(modelMapper.map(task, TaskEntity.class));
        } catch (IllegalArgumentException e) {
            throw new TaskCustomErrorException(e.getCause());
        }
    }

    @Override
    public Task getTask(int id) {
        return modelMapper.map( taskJpaRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id)), Task.class);
    }

    @Override
    public List<Task> getTaskByDescription(String description) {
        List<TaskEntity> taskEntityList = taskJpaRepository.findByDescriptionContaining(description);
        List<Task> tasks = taskEntityList.stream()
                .map(task -> modelMapper.map(task, Task.class))
                .collect(Collectors.toList());
        if (tasks.isEmpty()) {
            throw new NoDataFoundException();
        }
        return tasks;
    }

    @Override
    public void updateTask(int id, Task task) {
        TaskEntity userFromDb = taskJpaRepository.getById(id);
        userFromDb.setDescription(task.getDescription());
        userFromDb.setType(task.getType());
        userFromDb.setActive(task.isActive());
        taskJpaRepository.save(userFromDb);
    }

    @Override
    public boolean deleteTask(int id) {
        try {
            taskJpaRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new TaskCustomErrorException(e.getMessage());
        }
        return true;
    }

    @Override
    public void deleteAllTask() {
        taskJpaRepository.deleteAll();
    }

    @Override
    public List<Task> getTasks() {
        return taskJpaRepository.findAll().stream()
                .map(taskEntity -> modelMapper.map(taskEntity, Task.class))
                .collect(Collectors.toList());
    }
}
