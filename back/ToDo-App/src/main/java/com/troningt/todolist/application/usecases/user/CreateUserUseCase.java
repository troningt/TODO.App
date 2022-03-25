package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.CreateUserService;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

}