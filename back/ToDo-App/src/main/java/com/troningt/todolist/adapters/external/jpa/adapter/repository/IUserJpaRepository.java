package com.troningt.todolist.adapters.external.jpa.adapter.repository;

import com.troningt.todolist.adapters.external.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface IUserJpaRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailIs (String email);
}
