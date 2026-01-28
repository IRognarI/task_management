package ru.visitor.task_management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.dto.TaskDto;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.service.TaskService;

@RestController
@RequestMapping("task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody NewTask newTask) {
        log.info("Получили данные для добавления задачи: {}", newTask);

        Task task = taskService.createTask(newTask);

        log.info("Вернули: {}", task);

        return taskMapper.taskToDto(task);
    }
}
