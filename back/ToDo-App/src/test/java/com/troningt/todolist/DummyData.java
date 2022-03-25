package com.troningt.todolist;


import com.troningt.todolist.adapters.controller.dto.TaskDTO;
import com.troningt.todolist.adapters.controller.dto.UserDTO;
import com.troningt.todolist.adapters.external.jpa.entity.TaskEntity;
import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;
import com.troningt.todolist.domain.Task;
import com.troningt.todolist.domain.User;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    static List<Task> listTask = new ArrayList<>();
    static List<TaskDTO> listTaskDTO = new ArrayList<>();
    static List<TaskEntity> taskEntityList = new ArrayList<>();

    public static List<Task> dummyTask() {
        Task task = Task.builder().id(1).description("Task 1").type(1).isActive(true).build();
        listTask.add(task);
        task = Task.builder().id(2).description("Task 2").type(2).isActive(true).build();
        listTask.add(task);
        task = Task.builder().id(3).description("Task 3").type(1).isActive(false).build();
        listTask.add(task);
        return listTask;
    }

    public static List<TaskDTO> dummyTaskDTO() {
        TaskDTO task = TaskDTO.builder().id(1).description("Task 1").type(1).isActive(true).build();
        listTaskDTO.add(task);
        task = TaskDTO.builder().id(2).description("Task 2").type(2).isActive(true).build();
        listTaskDTO.add(task);
        task = TaskDTO.builder().id(3).description("Task 3").type(1).isActive(false).build();
        listTaskDTO.add(task);
        return listTaskDTO;
    }

    public static List<TaskEntity> dummyTaskEntity() {
        TaskEntity task = TaskEntity.builder().id(1).description("Task 1").type(1).isActive(true).build();
        taskEntityList.add(task);
        task = TaskEntity.builder().id(2).description("Task 2").type(2).isActive(true).build();
        taskEntityList.add(task);
        task = TaskEntity.builder().id(3).description("Task 3").type(1).isActive(false).build();
        taskEntityList.add(task);
        task = TaskEntity.builder().id(4).description("Task 4").type(1).isActive(false).build();
        taskEntityList.add(task);
        task = TaskEntity.builder().description("Task 5").type(1).isActive(false).build();
        taskEntityList.add(task);

        return taskEntityList;
    }

    public static List<User> dummyUser() {
        List<User> list = new ArrayList<>();
        User user = User.builder().id(1).username("User 1").email("user1@email.com").role(1).isActive(true).build();
        list.add(user);
        user = User.builder().id(2).username("User 2").email("user2@email.com").role(1).isActive(true).build();
        list.add(user);
        user = User.builder().id(3).username("User 3").email("user3@email.com").role(2).isActive(false).build();
        list.add(user);
        return list;
    }

    public static List<UserDTO> dummyUserDTO() {
        List<UserDTO> list = new ArrayList<>();
        UserDTO user = UserDTO.builder().username("User 1").password("***").email("user1@email.com").role(1).isActive(true).build();
        list.add(user);
        user = UserDTO.builder().username("User 2").password("***").email("user2@email.com").role(1).isActive(true).build();
        list.add(user);
        user = UserDTO.builder().username("User 3").password("***").email("user3@email.com").role(2).isActive(false).build();
        list.add(user);
        return list;
    }

    public static List<UserEntity> dummyUserEntity() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity user = UserEntity.builder().id(1).username("User 1").password("***").email("user1@email.com").role(1).isActive(true).build();
        list.add(user);
        user = UserEntity.builder().id(2).username("User 2").password("***").email("user2@email.com").role(1).isActive(true).build();
        list.add(user);
        user = UserEntity.builder().id(3).username("User 3").password("***").email("user3@email.com").role(2).isActive(false).build();
        list.add(user);
        user = UserEntity.builder().id(4).username("User 3").password("***").email("user4@email.com").role(2).isActive(false).build();
        list.add(user);
        return list;
    }
}
