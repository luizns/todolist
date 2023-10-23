package io.github.luizns.todolist.service;

import io.github.luizns.todolist.domain.model.Task;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;


public interface TaskService {
    Task create(Task task, HttpServletRequest request);

    List<Task> list(HttpServletRequest request);

    Task update(Task taskModel, @PathVariable UUID id, HttpServletRequest request);

    void deleteTaskById(UUID taskId);
}