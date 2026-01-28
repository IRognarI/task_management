package ru.visitor.task_management.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.visitor.task_management.dto.NewTask;
import ru.visitor.task_management.enums.TaskStatus;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private NewTask newTaskWithValidParam;

    private Task task;

    @BeforeEach
    void setup() {
        newTaskWithValidParam = new NewTask("Забрать вещи из химчистки", "Задача бытового назначения");

        task = new Task(newTaskWithValidParam.getName(), newTaskWithValidParam.getDescription());
    }

    @Test
    void createTask_ParamIsValid() {
        Mockito.when(taskRepository.save(Mockito.any(Task.class)))
                .thenReturn(task);

        Mockito.when(taskMapper.fromNewTaskToTask(Mockito.any(NewTask.class)))
                .thenReturn(task);

        Task finalTask = taskService.createTask(newTaskWithValidParam);

        Assertions.assertNull(finalTask.getId());
        Assertions.assertNull(finalTask.getStart());
        Assertions.assertNull(finalTask.getEnd());
        Assertions.assertEquals(newTaskWithValidParam.getName(), finalTask.getName());
        Assertions.assertEquals(newTaskWithValidParam.getDescription(), finalTask.getDescription());
        Assertions.assertEquals(TaskStatus.NEW, finalTask.getStatus());
    }
}