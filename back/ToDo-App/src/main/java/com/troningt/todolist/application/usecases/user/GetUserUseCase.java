package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.GetUserService;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCase implements GetUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(int id) {
        return userRepository.getUser(id);
    }
}