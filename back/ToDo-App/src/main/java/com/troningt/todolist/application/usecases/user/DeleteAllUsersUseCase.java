package com.troningt.todolist.application.usecases.user;

import com.troningt.todolist.application.port.in.user.DeleteAllUsersService;
import com.troningt.todolist.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAllUsersUseCase implements DeleteAllUsersService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAllUsers();
    }
}