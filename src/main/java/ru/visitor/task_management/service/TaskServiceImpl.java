package ru.visitor.task_management.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.repository.TaskRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public Task createTask(NewTask newTask) {

        checkNull(newTask);

        Task task = taskMapper.fromNewTaskToTask(newTask);

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    private <T extends NewTask> void checkNull(T obj) {
        if (obj == null) throw new ValidationException("No valid argument: " + obj);
    }
}
