package ru.visitor.task_management.dto;

import lombok.Getter;
import ru.visitor.task_management.enums.TaskStatus;

import java.time.LocalDateTime;

@Getter
public class TaskDto extends Dto {

    private Long id;

    private TaskStatus status;

    private LocalDateTime start;

    private LocalDateTime end;

    public TaskDto(long id, String name, String description, TaskStatus status, LocalDateTime start, LocalDateTime end) {
        setName(name);
        setDescription(description);
        this.id = id;
        this.status = status;

        if (start != null) this.start = start;

        if (end != null) this.end = end;
    }
}
