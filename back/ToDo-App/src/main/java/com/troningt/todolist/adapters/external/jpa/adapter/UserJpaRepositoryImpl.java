package com.troningt.todolist.adapters.external.jpa.adapter;

import com.troningt.todolist.adapters.external.jpa.adapter.repository.IUserJpaRepository;
import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;
import com.troningt.todolist.application.port.out.UserRepository;
import com.troningt.todolist.config.exception.UserCustomErrorException;
import com.troningt.todolist.config.exception.UserNotFoundException;
import com.troningt.todolist.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserJpaRepositoryImpl implements UserRepository {
    @Autowired
    IUserJpaRepository userJpaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createUser(User user) {
        try {
            userJpaRepository.save(modelMapper.map(user, UserEntity.class));
        } catch (IllegalArgumentException e) {
            throw new UserCustomErrorException(e.getCause());
        }
    }

    @Override
    public User getUser(int id) {
        return modelMapper.map(userJpaRepository.findById(id).orElseThrow(() ->
                        new UserNotFoundException(id)), User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userFound = userJpaRepository.findByEmailIs(email);
        if (userFound == null) {
            throw new UserNotFoundException(email);
        }
        return modelMapper.map(userFound, User.class);
    }

    @Override
    public void updateUser(int id, User user) {
        try {
            UserEntity userFromDb = userJpaRepository.getById(id);
            BeanUtils.copyProperties(user, userFromDb);
            userJpaRepository.save(userFromDb);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            userJpaRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new UserCustomErrorException(e.getMessage());
        }
        return true;
    }

    @Override
    public void deleteAllUsers() {
        userJpaRepository.deleteAll();
    }

    @Override
    public List<User> getUsers() {
        return userJpaRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }
}
