package com.troningt.todolist.adapters.controller;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.adapters.controller.dto.TaskDTO;
import com.troningt.todolist.adapters.controller.dto.model.Response;
import com.troningt.todolist.adapters.util.Utilities;
import com.troningt.todolist.application.port.in.task.*;
import com.troningt.todolist.config.exception.TaskCustomErrorException;
import com.troningt.todolist.config.exception.TaskNotFoundException;
import com.troningt.todolist.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TaskController.class)
class TaskControllerTest {
    @MockBean
    private GetTaskService getTaskService;

    @MockBean
    private GetAllTasksService getAllTasksService;

    @MockBean
    private GetTaskByDescriptionService getTaskByDescriptionService;

    @MockBean
    private UpdateTaskService updateTaskService;

    @MockBean
    private DeleteTaskService deleteTaskService;

    @MockBean
    private DeleteAllTasksService deleteAllTasksService;

    @MockBean
    private CreateTaskService createTaskService;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    Utilities<TaskDTO> utilities;

    @Autowired
    private TaskController taskController;

    @Test
    void createTaskOKTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response(any()));
        assertEquals("200 OK", taskController.createTask(any()).getStatusCode().toString());
    }

    @Test
    void createTaskCustomExceptionTest() {
        doThrow(new TaskCustomErrorException()).when(createTaskService).createTask(any());
        assertThrows(TaskCustomErrorException.class, () -> taskController.createTask(DummyData.dummyTaskDTO().get(0)));
    }

    @Test
    void getTaskByDescriptionOKTest() {
        when(getTaskByDescriptionService.getTaskByDescription(any())).thenReturn(DummyData.dummyTask());
        assertEquals("200 OK", taskController.getTaskByDescription(any()).getStatusCode().toString());
    }

    @Test
    void getTaskByDescriptionNullTest() {
        List<Task> listMapper = modelMapper.map(DummyData.dummyTaskDTO(), new TypeToken<List<Task>>() {}.getType());
        when(getTaskByDescriptionService.getTaskByDescription(any())).thenReturn(listMapper);
        assertThrows(NullPointerException.class, () -> {
            taskController.getTaskByDescription(any());
        });
    }

    @Test
    void getTaskOkTest() {
        when(getTaskService.getTask(1)).thenReturn(DummyData.dummyTask().get(0));
        assertEquals("200 OK", taskController.getTask(1).getStatusCode().toString());
    }

    @Test
    void getTaskNotFoundTest() {
        when(getTaskService.getTask(anyInt())).thenThrow(new TaskNotFoundException(anyInt()));
        assertThrows(TaskNotFoundException.class, ()-> taskController.getTask(1));
    }

    @Test
    void getTaskNotNullTest() {
        when(getTaskService.getTask(anyInt())).thenReturn(DummyData.dummyTask().get(0));
        Assertions.assertNotNull(taskController.getTask(anyInt()));
    }

    @Test
    void updateTaskOkTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response(any()));
        assertEquals("200 OK", taskController.updateTask(anyInt(), DummyData.dummyTaskDTO().get(0)).getStatusCode().toString());
    }

    @Test
    void deleteTaskOkTest() {
        when(utilities.generateResponse(any())).thenReturn(any(Response.class));
        assertEquals("200 OK", taskController.deleteTask(1).getStatusCode().toString());
    }

    @Test
    void deleteTaskCustomExceptionTest() {
        when(deleteTaskService.deleteTask(anyInt())).thenThrow(new TaskCustomErrorException("message", new Throwable("message")));
        assertThrows(TaskCustomErrorException.class, () -> taskController.deleteTask(1));
    }

    @Test
    void deleteAllTasksOKTest() {
        assertEquals("200 OK", taskController.deleteAllTasks().getStatusCode().toString());
    }

    @Test
    void getAllTasksOkTest() {
        when(getAllTasksService.getTasks()).thenReturn(DummyData.dummyTask());
        assertEquals("200 OK", taskController.getAllTasks().getStatusCode().toString());
    }
}