package io.github.luizns.todolist.controller;

import io.github.luizns.todolist.domain.model.Task;
import io.github.luizns.todolist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> list(HttpServletRequest request) {
        return service.list(request);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task, HttpServletRequest request) {
        var taskCreated = service.create(task, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(taskCreated);
    }
}