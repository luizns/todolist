package io.github.luizns.todolist.mapper;

import io.github.luizns.todolist.controller.DTO.TaskDTO;
import io.github.luizns.todolist.controller.DTO.TaskRequestDTO;
import io.github.luizns.todolist.domain.model.Task;

public class TaskConverter
{

    public static Task dtoToEntity(TaskRequestDTO request)
    {
        return Task.builder()
                .description(request.getDescription())
                .title(request.getTitle())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .priority(request.getPriority())
                .idUser(request.getIdUser())
                .build();
    }

    public static TaskDTO entityToDto(Task entity)
    {

        return TaskDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .title(entity.getTitle())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .priority(entity.getPriority())
                .idUser(entity.getIdUser())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
