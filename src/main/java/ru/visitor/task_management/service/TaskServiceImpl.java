package ru.visitor.task_management.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.exception.NotFoundException;
import ru.visitor.task_management.exception.ValidationException;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.repository.TaskRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public Task createTask(NewTask newTask) throws ValidationException {

        checkNull(newTask);

        log.info("Получили данные для добавления новой задачи: {}", newTask);

        Task task = taskRepository.save(taskMapper.fromNewTaskToTask(newTask));

        log.info("Вернули объект: {}", task);

        return task;
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) throws ValidationException {

        idValidate(id);

        log.info("Получили id: {} для получения задачи", id);

        Task targetTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Задача с id=" + id + " не найдена"));

        log.info("Вернули задачу: {}", targetTask);

        return targetTask;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(Integer limit) {
        return taskRepository.getAllAndLimit(limit);
    }

    private <T extends NewTask> void checkNull(T obj) {
        if (obj == null) throw new ValidationException("No valid argument: " + obj);
    }

    private void idValidate(Long id) throws ValidationException {

        if (id == null || id < 1) {
            throw new ValidationException("Invalid id: " + id);
        }
    }
}
