package com.troningt.todolist.application.usecases;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.application.usecases.user.*;
import com.troningt.todolist.domain.User;
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
class UserUseCaseTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    CreateUserUseCase createUserUseCase;

    @Autowired
    GetUserUseCase getUserUseCase;

    @Autowired
    GetUserByEmailUseCase getUserByEmailUseCase;

    @Autowired
    GetAllUsersUseCase getAllUsersUseCase;

    @Autowired
    UpdateUserUseCase updateUserUseCase;

    @Autowired
    DeleteUserUseCase deleteUserUseCase;

    @Autowired
    DeleteAllUsersUseCase deleteAllUsersUseCase;

    User user = DummyData.dummyUser().get(0);

    @BeforeEach
    void setup() {
        doNothing().when(userRepository).createUser(any());
        when(userRepository.getUser(anyInt())).thenReturn(user);
        when(userRepository.getUserByEmail(anyString())).thenReturn(user);
        when(userRepository.getUsers()).thenReturn(DummyData.dummyUser());
        doNothing().when(userRepository).updateUser(anyInt(), any());
        when(userRepository.deleteUser(anyInt())).thenReturn(true);
        doNothing().when(userRepository).deleteAllUsers();
    }

    @Test
    @DisplayName("Test for CreateUserUseCase")
    void createUserOkTest() {
        createUserUseCase.createUser(any());
        verify(userRepository).createUser(any());
    }

    @Test
    @DisplayName("Test for GetUserUseCase")
    void getUserOkTest() {
        assertEquals(user.getId(), getUserUseCase.getUser(anyInt()).getId());
    }

    @Test
    @DisplayName("Test for GetUserByEmailUseCase")
    void getUserByEmailOkTest() {
        assertEquals(user.getEmail(), getUserByEmailUseCase.getUserByEmail(anyString()).getEmail());
    }

    @Test
    @DisplayName("Test for GetAllUsersUseCase")
    void getUsersOkTest() {
        assertFalse(getAllUsersUseCase.getUsers().isEmpty());
    }

    @Test
    @DisplayName("Test for UpdateUserUseCase")
    void updateUserOkTest() {
        updateUserUseCase.updateUser(anyInt(), any());
        verify(userRepository).updateUser(anyInt(), any());
    }

    @Test
    @DisplayName("Test for DeleteUserUseCase")
    void deleteUserOkTest() {
        assertTrue(deleteUserUseCase.deleteUser(anyInt()));
    }

    @Test
    @DisplayName("Test for DeleteAllUsersUseCase")
    void deleteAllUsersOkTest() {
        deleteAllUsersUseCase.deleteAllUsers();
        verify(userRepository).deleteAllUsers();
    }
}