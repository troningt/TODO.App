package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.GetUserByEmailService;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByEmailUseCase implements GetUserByEmailService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
