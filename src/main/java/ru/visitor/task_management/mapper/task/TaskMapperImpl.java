package ru.visitor.task_management.mapper.task;

import org.springframework.stereotype.Component;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.dto.TaskDto;
import ru.visitor.task_management.model.Task;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto taskToDto(Task task) {

        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getStart(),
                task.getEnd()
        );
    }

    @Override
    public Task fromNewTaskToTask(NewTask newTask) {

        return new Task(
                newTask.getName(), newTask.getDescription());
    }
}
