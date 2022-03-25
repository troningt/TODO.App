package com.troningt.todolist.adapters.controller;

import com.troningt.todolist.DummyData;
import com.troningt.todolist.adapters.controller.dto.UserDTO;
import com.troningt.todolist.adapters.controller.dto.model.Response;
import com.troningt.todolist.adapters.util.Utilities;
import com.troningt.todolist.application.port.in.user.*;
import com.troningt.todolist.config.exception.UserCustomErrorException;
import com.troningt.todolist.config.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserController.class)
class UserControllerTest {
    @MockBean
    private GetUserService getUserService;

    @MockBean
    private GetAllUsersService getAllUsersService;

    @MockBean
    private GetUserByEmailService getUserByEmailService;

    @MockBean
    private UpdateUserService updateUserService;

    @MockBean
    private DeleteUserService deleteUserService;

    @MockBean
    private DeleteAllUsersService deleteAllUsersService;

    @MockBean
    private CreateUserService createUserService;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    Utilities<UserDTO> utilities;

    @Autowired
    private UserController userController;


    @Test
    void createUserOkTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response(any()));
        assertEquals("200 OK", userController.createUser(any()).getStatusCode().toString());
    }

    @Test
    void createUserCustomExceptionTest() {
        doThrow(new UserCustomErrorException()).when(createUserService).createUser(any());
        assertThrows(UserCustomErrorException.class, () -> userController.createUser(DummyData.dummyUserDTO().get(0)));
    }

    @Test
    void createUserCustomExceptionMessageTest() {
        doThrow(new UserCustomErrorException("message")).when(createUserService).createUser(any());
        assertThrows(UserCustomErrorException.class, () -> userController.createUser(DummyData.dummyUserDTO().get(0)));
    }

    @Test
    void getUserByEmailOKTest() {
        when(getUserByEmailService.getUserByEmail(anyString())).thenReturn(DummyData.dummyUser().get(0));
        assertEquals("200 OK", userController.getUserByEmail(anyString()).getStatusCode().toString());
    }

    @Test
    void getUserOKTest() {
        when(getUserService.getUser(anyInt())).thenReturn(DummyData.dummyUser().get(0));
        assertEquals("200 OK", userController.getUser(1).getStatusCode().toString());
    }

    @Test
    void getUserNotFoundTest() {
        when(getUserService.getUser(anyInt())).thenThrow(new UserNotFoundException(anyInt()));
        assertThrows(UserNotFoundException.class, ()->{
            getUserService.getUser(1);
        });
    }

    @Test
    void getUserNotFoundByEmailTest() {
        when(getUserByEmailService.getUserByEmail(anyString())).thenThrow(new UserNotFoundException("email@acl.cl"));
        assertThrows(UserNotFoundException.class, () -> getUserByEmailService.getUserByEmail(anyString()));
    }

    @Test
    void updateUserOKTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response("any"));
        assertEquals("200 OK", userController.updateUser(anyInt(), any()).getStatusCode().toString());
    }

    @Test
    void updateUserCustomExceptionTest() {
        doThrow(new UserNotFoundException(1)).when(updateUserService).updateUser(anyInt(), any());
        assertThrows(UserNotFoundException.class, () -> userController.updateUser(1, DummyData.dummyUserDTO().get(0)));
    }

    @Test
    void deleteUserOKTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response(any()));
        assertEquals("200 OK", userController.deleteUser(1).getStatusCode().toString());
    }

    @Test
    void deleteAllUsersOkTest() {
        when(utilities.generateResponse(any())).thenReturn(new Response(""));
        assertEquals("200 OK", userController.deleteAllUsers().getStatusCode().toString());
    }

    @Test
    void getAllUsersOkTest() {
        when(getAllUsersService.getUsers()).thenReturn(DummyData.dummyUser());
        if (userController.getAllUsers().getBody() != null) {
            assertTrue(userController.getAllUsers().getBody().size() > 0);
        }
    }
}