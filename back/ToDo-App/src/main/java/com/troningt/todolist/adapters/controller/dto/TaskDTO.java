package com.troningt.todolist.adapters.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.troningt.todolist.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private int id;
    @NotBlank
    private String description;
    @Min(0)
    private int type;
    private boolean isActive;

    public static TaskDTO of(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .type(task.getType())
                .isActive(task.isActive())
                .build();
    }
}
