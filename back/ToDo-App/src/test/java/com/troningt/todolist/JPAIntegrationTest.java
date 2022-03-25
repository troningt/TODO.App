package com.troningt.todolist;

import com.troningt.todolist.adapters.external.jpa.adapter.repository.ITaskJpaRepository;
import com.troningt.todolist.adapters.external.jpa.adapter.repository.IUserJpaRepository;
import com.troningt.todolist.adapters.external.jpa.entity.TaskEntity;
import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class JPAIntegrationTest {

    @Autowired
    ITaskJpaRepository taskRepository;

    @Autowired
    IUserJpaRepository userJpaRepository;

    @Test
    @DisplayName("Test find by id task")
    void findByIdTaskTest() {
       Optional<TaskEntity> task = taskRepository.findById(1);
       assertTrue(task.isPresent());
       assertEquals(DummyData.dummyTaskEntity().get(0), task.orElseThrow());
    }

    @Test
    @DisplayName("Test find all tasks")
    void findAllTasksTest() {
        List<TaskEntity> tasks = taskRepository.findAll();
        assertFalse(tasks.isEmpty());
        assertEquals(3, tasks.size());
    }

    @Test
    @DisplayName("Test for save a task")
    void saveTaskTest() {
        TaskEntity task = DummyData.dummyTaskEntity().get(3);
        taskRepository.save(task);

        TaskEntity taskFound = taskRepository.findById(task.getId()).orElseThrow();
        assertEquals(taskFound, task);
    }

    @Test
    @DisplayName("Test for update a task")
    void updateTaskTest() {
        TaskEntity task = DummyData.dummyTaskEntity().get(3);
        TaskEntity taskSaved = taskRepository.save(task);

        TaskEntity taskFound = taskRepository.findById(taskSaved.getId()).orElseThrow();
        taskFound.setActive(false);
        taskFound.setDescription("Task edited");
        TaskEntity taskUpdated = taskRepository.save(taskFound);

        assertEquals("Task edited", taskUpdated.getDescription());
        assertFalse(taskUpdated.isActive());
    }

    @Test
    @DisplayName("Test for delete task")
    void deleteTaskTest() {
        Optional<TaskEntity> taskFound = taskRepository.findById(1);
        assertTrue(taskFound.isPresent());

        taskRepository.delete(taskFound.orElseThrow());
        assertThrows(NoSuchElementException.class, () -> {taskRepository.findById(1).orElseThrow();});
    }

    @Test
    @DisplayName("Test find by description user")
    void findByDescriptionTaskTest() {
        taskRepository.save(DummyData.dummyTaskEntity().get(3));
        List<TaskEntity> tasks = taskRepository.findByDescriptionContaining("Task 4");
        assertEquals("Task 4", tasks.get(0).getDescription());
    }

    @Test
    @DisplayName("Test find by id user")
    void findByIdUserTest() {
        Optional<UserEntity> user = userJpaRepository.findById(1);
        assertTrue(user.isPresent());
        assertEquals(DummyData.dummyUserEntity().get(0), user.orElseThrow());
    }

    @Test
    @DisplayName("Test find all users")
    void findAllUsersTest() {
        List<UserEntity> users = userJpaRepository.findAll();
        assertFalse(users.isEmpty());
        assertEquals(3, users.size());
    }

    @Test
    @DisplayName("Test find by description task")
    void findByEmailIsUserTest() {
        userJpaRepository.save(DummyData.dummyUserEntity().get(3));
        UserEntity user = userJpaRepository.findByEmailIs(DummyData.dummyUserEntity().get(3).getEmail());
        assertEquals(DummyData.dummyUserEntity().get(3).getEmail(), user.getEmail());
    }
}
