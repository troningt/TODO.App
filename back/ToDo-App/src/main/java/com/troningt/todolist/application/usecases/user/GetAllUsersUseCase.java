package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.GetAllUsersService;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase implements GetAllUsersService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}