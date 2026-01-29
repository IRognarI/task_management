package ru.visitor.task_management.service;

import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(NewTask newTask);

    Task getTaskById(Long id);

    List<Task> getAllTasks(Integer limit);
}
