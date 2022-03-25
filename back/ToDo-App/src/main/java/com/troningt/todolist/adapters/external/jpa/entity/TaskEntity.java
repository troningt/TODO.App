package com.troningt.todolist.adapters.external.jpa.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;


@Entity
@Table(name = "tasks")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String description;
    private int type;
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskEntity task = (TaskEntity) o;
        return Objects.equals(id, task.id)
                && Objects.equals(description, task.getDescription())
                && Objects.equals(type, task.getType())
                && Objects.equals(isActive, task.isActive());
    }
}
