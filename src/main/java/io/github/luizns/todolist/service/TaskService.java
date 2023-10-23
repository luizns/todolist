package io.github.luizns.todolist.service;

import io.github.luizns.todolist.controller.DTO.TaskDTO;
import io.github.luizns.todolist.controller.DTO.TaskRequestDTO;
import io.github.luizns.todolist.domain.model.Task;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


public interface TaskService {
    TaskDTO create(TaskRequestDTO task, HttpServletRequest request);

    List<TaskDTO> list(HttpServletRequest request);

    Task update(Task taskModel, @PathVariable UUID id, HttpServletRequest request);

    void deleteTaskById(UUID taskId);
}