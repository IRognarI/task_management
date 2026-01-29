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
import ru.visitor.task_management.exception.NotFoundException;
import ru.visitor.task_management.exception.ValidationException;
import ru.visitor.task_management.mapper.task.TaskMapper;
import ru.visitor.task_management.model.Task;
import ru.visitor.task_management.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

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

    @Test
    void createTask_ShouldBeValidationExceptionWhenObjectIsNull() {
        Assertions.assertThrows(ValidationException.class, () -> taskService.createTask(null));
    }

    @Test
    public void getTaskById_shouldBeCorrect() {
        Mockito.when(taskRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(task));

        Task targetTask = taskService.getTaskById(23L);

        Assertions.assertEquals(newTaskWithValidParam.getName(), targetTask.getName());
        Assertions.assertEquals(newTaskWithValidParam.getDescription(), targetTask.getDescription());
    }

    @Test
    public void getTaskById_shouldBeValidationException() {
        Assertions.assertThrows(ValidationException.class, () -> taskService.getTaskById(null));
    }

    @Test
    public void getTaskById_shouldBeNotFoundException() {
        Mockito.when(taskRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> taskService.getTaskById(23L));
    }

    @Test
    public void getAllTasks_Should_Be_List_Size_Equal_Ten_When_Limit_Is_Null() {
        List<Task> taskList = List.of(
                new Task("Задача-1", "Описание задачи-1"),
                new Task("Задача-2", "Описание задачи-2"),
                new Task("Задача-3", "Описание задачи-3"),
                new Task("Задача-4", "Описание задачи-4"),
                new Task("Задача-5", "Описание задачи-5"),
                new Task("Задача-6", "Описание задачи-6"),
                new Task("Задача-7", "Описание задачи-7"),
                new Task("Задача-8", "Описание задачи-8"),
                new Task("Задача-9", "Описание задачи-9"),
                new Task("Задача-10", "Описание задачи-10")
        );

        Mockito.when(taskRepository.getAllAndLimit(Mockito.isNull()))
                        .thenReturn(taskList);

        Assertions.assertEquals(taskList.size(), taskService.getAllTasks(null).size());
    }
}