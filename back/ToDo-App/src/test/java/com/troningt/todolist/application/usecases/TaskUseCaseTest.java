package com.troningt.todolist.application.usecases;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.application.port.out.TaskRepository;
import com.troningt.todolist.application.usecases.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskUseCaseTest {

    @MockBean
    TaskRepository taskRepository;

    @Autowired
    CreateTaskUseCase createTaskUseCase;

    @Autowired
    GetTaskUseCase getTaskUseCase;

    @Autowired
    GetTaskByDescriptionUseCase getTaskByDescriptionUseCase;

    @Autowired
    GetAllTasksUseCase getAllTasksUseCase;

    @Autowired
    UpdateTaskUseCase updateTaskUseCase;

    @Autowired
    DeleteTaskUseCase deleteTaskUseCase;

    @Autowired
    DeleteAllTaskUseCase deleteAllTaskUseCase;

    @BeforeEach
    void setup() {
        doNothing().when(taskRepository).createTask(any());
        when(taskRepository.getTask(anyInt())).thenReturn(DummyData.dummyTask().get(0));
        when(taskRepository.getTasks()).thenReturn(DummyData.dummyTask());
        when(taskRepository.getTaskByDescription(anyString())).thenReturn(DummyData.dummyTask());
        doNothing().when(taskRepository).updateTask(anyInt(), any());
        when(taskRepository.deleteTask(anyInt())).thenReturn(true);
        doNothing().when(taskRepository).deleteAllTask();
    }

    @Test
    @DisplayName("Test for CreateTaskUseCase")
    void createTaskOkTest() {
        createTaskUseCase.createTask(any());
        verify(taskRepository).createTask(any());
    }

    @Test
    @DisplayName("Test for GetTaskUseCase")
    void getTaskOkTest() {
        assertEquals(1, getTaskUseCase.getTask(anyInt()).getId());
    }

    @Test
    @DisplayName("Test for GetTaskByDescriptionUseCase")
    void getTaskByDescriptionOkTest() {
        assertFalse(getTaskByDescriptionUseCase.getTaskByDescription(anyString()).isEmpty());
    }

    @Test
    @DisplayName("Test for GetAllTasksUseCase")
    void getTasksOkTest() {
        assertFalse(getAllTasksUseCase.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Test for UpdateTaskUseCase")
    void updateTaskOkTest() {
        updateTaskUseCase.updateTask(anyInt(), any());
        verify(taskRepository).updateTask(anyInt(), any());
    }

    @Test
    @DisplayName("Tst for DeleteTaskUseCase")
    void deleteTaskOkTest() {
        deleteTaskUseCase.deleteTask(anyInt());
        verify(taskRepository).deleteTask(anyInt());
    }

    @Test
    @DisplayName("Test for DeleteAllTaskUseCase")
    void deleteAllTasksOkTest() {
        deleteAllTaskUseCase.deleteAllTasks();
        verify(taskRepository).deleteAllTask();
    }
}