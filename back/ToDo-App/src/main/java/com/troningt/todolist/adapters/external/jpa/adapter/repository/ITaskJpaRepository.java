package com.troningt.todolist.adapters.external.jpa.adapter.repository;

import com.troningt.todolist.adapters.external.jpa.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ITaskJpaRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByDescriptionContaining(String description);
}
