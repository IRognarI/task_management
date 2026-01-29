package ru.visitor.task_management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.dto.TaskDto;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.repository.TaskRepository;
import ru.visitor.task_management.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody NewTask newTask) {
        log.info("Получили данные для добавления задачи: {}", newTask);

        Task task = taskService.createTask(newTask);

        log.info("Вернули: {}", task);

        return taskMapper.taskToDto(task);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTaskById(@PathVariable("id") Long taskId) {
        log.info("Получили id= {} для получения задачи", taskId);

        Task task = taskService.getTaskById(taskId);

        log.info("Вернули задачу: {}", task);

        return taskMapper.taskToDto(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTasks(@RequestParam(required = false, defaultValue = "10", name = "limit") Integer limit) {
        log.info("Получили запрос на получение всех задач");

        List<Task> taskList = taskService.getAllTasks(limit);

        log.info("Вернули список размером: {}", taskList.size());

        return taskList.stream()
                .map(t -> taskMapper.taskToDto(t))
                .toList();
    }
}
