package io.github.luizns.todolist.service;

import io.github.luizns.todolist.domain.model.Task;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface TaskService {
    Task create(Task task, HttpServletRequest request);
    List<Task> list(HttpServletRequest request);
}
