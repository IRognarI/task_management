package ru.visitor.task_management.mapper.task;

import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.dto.TaskDto;
import ru.visitor.task_management.model.Task;

public interface TaskMapper {
    TaskDto taskToDto(Task task);

    Task fromNewTaskToTask(NewTask newTask);
}