package io.github.luizns.todolist.controller.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@With
public class TaskDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String description;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;
    private  LocalDateTime updatedAt;

}
