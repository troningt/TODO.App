package com.troningt.todolist.adapters.external.jpa.adapter;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.adapters.external.jpa.adapter.repository.ITaskJpaRepository;
import com.troningt.todolist.adapters.external.jpa.entity.TaskEntity;
import com.troningt.todolist.config.exception.NoDataFoundException;
import com.troningt.todolist.config.exception.TaskCustomErrorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TaskJpaRepositoryImpl.class)
class TaskJpaRepositoryImplTest {

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    ITaskJpaRepository taskJpaRepository;

    @Autowired
    TaskJpaRepositoryImpl taskJpaRepositoryImpl;

    @Test
    void createTaskTest() {
        when(taskJpaRepository.save(any())).thenReturn(DummyData.dummyTaskEntity().get(0));
        taskJpaRepositoryImpl.createTask(DummyData.dummyTask().get(0));
        verify(taskJpaRepository, times(1)).save(any());
    }

    @Test
    void getTaskOKTest() {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyTask().get(0));
        when(taskJpaRepository.findById(any())).thenReturn(Optional.of(DummyData.dummyTaskEntity().get(0)));
        assertNotNull(taskJpaRepositoryImpl.getTask(1));
    }

    @Test
    void getTaskNullTest() {
        when(taskJpaRepository.findById(any())).thenReturn(Optional.of(DummyData.dummyTaskEntity().get(0)));
        assertNull(taskJpaRepositoryImpl.getTask(1));
    }

    @Test
    void getTaskByDescriptionOkTest() {
        when(taskJpaRepository.findByDescriptionContaining(any())).thenReturn(DummyData.dummyTaskEntity());
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyTask().get(0));
        assertTrue(taskJpaRepositoryImpl.getTaskByDescription("demo").size() > 0);
    }

    @Test
    void getTaskByDescriptionNoDataFoundExceptionTest() {
        when(taskJpaRepository.findByDescriptionContaining(any())).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class,() -> taskJpaRepositoryImpl.getTaskByDescription("demo"));
    }


    @Test
    void updateTaskOkTest() {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyTask().get(0));
        when(taskJpaRepository.getById(any())).thenReturn(DummyData.dummyTaskEntity().get(0));
        when(taskJpaRepository.save(any())).thenReturn(any(TaskEntity.class));
        taskJpaRepositoryImpl.updateTask(1, DummyData.dummyTask().get(0));
        verify(taskJpaRepository, times(1)).getById(any());
        verify(taskJpaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete task")
    void deleteTaskOkTest() {
        doNothing().when(taskJpaRepository).delete(any());
        taskJpaRepositoryImpl.deleteTask(1);
        verify(taskJpaRepository).deleteById(any());
    }

    @Test
    @DisplayName("Test delete task with TaskCustomErrorException")
    void deleteTaskIllegalArgumentExceptionTest() {
        doThrow(IllegalArgumentException.class).when(taskJpaRepository).deleteById(any());
        assertThrows(TaskCustomErrorException.class, () -> taskJpaRepositoryImpl.deleteTask(1));
        verify(taskJpaRepository).deleteById(any());
    }

    @Test
    @DisplayName("Test delete all task")
    void deleteAllTasksOkTest() {
        taskJpaRepositoryImpl.deleteAllTask();
        verify(taskJpaRepository).deleteAll();
    }
    @Test
    @DisplayName("Test get all tasks")
    void getTasksOkTest() {
        when(taskJpaRepository.findAll()).thenReturn(DummyData.dummyTaskEntity());
        when(modelMapper.map(any(),any())).thenReturn(DummyData.dummyTask().get(0));
        assertTrue(taskJpaRepositoryImpl.getTasks().size() > 0);
    }
}