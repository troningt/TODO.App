package com.troningt.todolist.adapters.external.jpa.adapter;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.adapters.external.jpa.adapter.repository.IUserJpaRepository;
import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;
import com.troningt.todolist.config.exception.UserCustomErrorException;
import com.troningt.todolist.config.exception.UserNotFoundException;
import com.troningt.todolist.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserJpaRepositoryImpl.class)
class UserJpaRepositoryImplTest {

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    IUserJpaRepository userJpaRepository;

    @Autowired
    UserJpaRepositoryImpl userJpaRepositoryImpl;

    @Test
    @DisplayName("Test ok create user")
    void createUserOkTest() {
        when(userJpaRepository.save(any())).thenReturn(DummyData.dummyUserEntity().get(0));
        userJpaRepositoryImpl.createUser(DummyData.dummyUser().get(0));
        verify(userJpaRepository).save(any());
    }

    @Test
    @DisplayName("Test create user wih UserCustomErrorException")
    void createUserUserCustomErrorExceptionTest() {
        doThrow(IllegalArgumentException.class).when(userJpaRepository).save(any());
        User user = DummyData.dummyUser().get(0);
        assertThrows(UserCustomErrorException.class, () ->
                userJpaRepositoryImpl.createUser(user));
    }

    @Test
    @DisplayName("Test get user")
    void getUserOkTest() {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyUser().get(0));
        when(userJpaRepository.findById(any())).thenReturn(Optional.of(DummyData.dummyUserEntity().get(0)));
        assertEquals(1, userJpaRepositoryImpl.getUser(1).getId());
        verify(userJpaRepository).findById(1);
    }

    @Test
    @DisplayName("Test get user with UserNotFoundException")
    void getUserUserNotFoundExceptionTest() {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyUser().get(0));
        when(userJpaRepository.findById(any())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> userJpaRepositoryImpl.getUser(1));
    }

    @Test
    @DisplayName("Test get user by email")
    void getUserByEmailOkTest() {
        when(userJpaRepository.findByEmailIs(any())).thenReturn(DummyData.dummyUserEntity().get(0));
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyUser().get(0));
        assertEquals("user1@email.com", userJpaRepositoryImpl.getUserByEmail(any()).getEmail());
        verify(userJpaRepository).findByEmailIs(any());
    }

    @Test
    @DisplayName("Test get user by email with UserNotFoundException")
    void getUserByEmailUserNotFoundExceptionTest() {
        when(userJpaRepository.findByEmailIs(any())).thenReturn(null);
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyUser().get(0));
        assertThrows(UserNotFoundException.class, () -> userJpaRepositoryImpl.getUserByEmail(any()));
    }

    @Test
    @DisplayName("Test for update user")
    void updateUserOkTest() {
        UserEntity user = DummyData.dummyUserEntity().get(0);
        when(userJpaRepository.getById(any())).thenReturn(user);
        when(userJpaRepository.save(any())).thenReturn(user);
        userJpaRepositoryImpl.updateUser(1, DummyData.dummyUser().get(0));
        verify(userJpaRepository).save(any());
        verify(userJpaRepository).getById(any());
    }

    @Test
    @DisplayName("Test for update user with UserNotFoundException")
    void updateUserUserNotFoundExceptionTest() {
        UserEntity user = DummyData.dummyUserEntity().get(0);
        when(userJpaRepository.getById(any())).thenThrow(EntityNotFoundException.class);
        User user1 = DummyData.dummyUser().get(0);
        assertThrows(UserNotFoundException.class, () -> userJpaRepositoryImpl.updateUser(1, user1));
    }

    @Test
    @DisplayName("Test for delete user")
    void deleteUserOkTest() {
        doNothing().when(userJpaRepository).deleteById(any());
        assertTrue(userJpaRepositoryImpl.deleteUser(1));
    }

    @Test
    @DisplayName("Test for delete user with UserCustomErrorException")
    void deleteUserUserCustomErrorExceptionTest() {
        doThrow(IllegalArgumentException.class).when(userJpaRepository).deleteById(any());
        assertThrows(UserCustomErrorException.class, () -> userJpaRepositoryImpl.deleteUser(1));
    }

    @Test
    @DisplayName("Test delete all users")
    void deleteAllUsers() {
        doNothing().when(userJpaRepository).deleteAll();
        userJpaRepositoryImpl.deleteAllUsers();
        verify(userJpaRepository).deleteAll();
    }

    @Test
    @DisplayName("Test for get all users")
    void getUsersOkTest() {
        when(userJpaRepository.findAll()).thenReturn(DummyData.dummyUserEntity());
        when(modelMapper.map(any(), any())).thenReturn(DummyData.dummyUser().get(0));
        assertFalse(userJpaRepositoryImpl.getUsers().isEmpty());
    }
}