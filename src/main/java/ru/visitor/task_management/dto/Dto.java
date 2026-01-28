package ru.visitor.task_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class Dto {

    @NotNull(message = "Название задачи не может быть пустым")
    @NotEmpty(message = "Название задачи не может быть пустым")
    @NotBlank(message = "Название задачи не может быть пустым")
    private String name;

    @Size(min = 10, message = "Минимальная длинна описания задачи: 10 символов")
    @NotNull(message = "Описание задачи не может быть пустым")
    @NotEmpty(message = "Описание задачи не может быть пустым")
    @NotBlank(message = "Описание задачи не может быть пустым")
    private String description;
}
