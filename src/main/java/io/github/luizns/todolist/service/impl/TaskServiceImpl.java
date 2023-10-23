package io.github.luizns.todolist.service.impl;

import io.github.luizns.todolist.controller.DTO.TaskDTO;
import io.github.luizns.todolist.controller.DTO.TaskRequestDTO;
import io.github.luizns.todolist.domain.model.Task;
import io.github.luizns.todolist.domain.repository.ITaskRepository;
import io.github.luizns.todolist.mapper.TaskConverter;
import io.github.luizns.todolist.service.TaskService;
import io.github.luizns.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final ITaskRepository repository;

    public TaskServiceImpl(ITaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaskDTO create(TaskRequestDTO task, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);
        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            throw new IllegalArgumentException("A data de início / data término deve ser maior que a data atual");
        }
        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new IllegalArgumentException("A data de início deve ser menor que a data de termino");
        }
        return TaskConverter.entityToDto( this.repository.save(TaskConverter.dtoToEntity(task)));

    }

    @Override
    public List<TaskDTO> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");

        return this.repository
                .findByIdUser((UUID) idUser)
                .stream().map(TaskConverter ::entityToDto)
                .toList();

    }

    @Override
    public Task update(Task taskModel, UUID id, HttpServletRequest request) {

        var task = this.repository.findById(id).orElse(null);

        if (task == null) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        var idUser = request.getAttribute("idUser");

        if (!task.getIdUser().equals(idUser)) {
            throw new IllegalArgumentException("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);

        return this.repository.save(task);
    }


    @Override
    public void deleteTaskById(UUID taskId) {
        repository.deleteById(taskId);
    }
}
