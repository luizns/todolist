package io.github.luizns.todolist.controller;

import io.github.luizns.todolist.domain.model.Task;
import io.github.luizns.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;



@CrossOrigin
@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Controller", description = "RESTful API for managing tasks.")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @Operation(security = @SecurityRequirement(name = "basicAuth"),summary = "Get all tasks", description = "Retrieve a list of all registered tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    @GetMapping
    public List<Task> list(HttpServletRequest request) {
        return service.list(request);
    }


    @Operation(security = @SecurityRequirement(name = "basicAuth"),summary = "Create a new task", description = "Create a new task and return the created task's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
    })
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task, HttpServletRequest request) {
        var taskCreated = service.create(task, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(taskCreated);
    }

    @Operation(security = @SecurityRequirement(name = "basicAuth"),summary = "Update a task", description = "Update the data of an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),

    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable UUID id, HttpServletRequest request) {
        var taskUpdated = service.update(task, id, request);
        return ResponseEntity.ok().body(taskUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "basicAuth"),summary = "Delete a task", description = "Delete an existing task based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}