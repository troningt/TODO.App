package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.UpdateUserService;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void updateUser(int id, User user) {
        userRepository.updateUser(id, user);
    }
}