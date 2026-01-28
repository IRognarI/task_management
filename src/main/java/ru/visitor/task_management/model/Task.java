package ru.visitor.task_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.visitor.task_management.enums.TaskStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tasks")
@EqualsAndHashCode
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotNull(message = "Название задачи не может быть пустым")
    @NotEmpty(message = "Название задачи не может быть пустым")
    @NotBlank(message = "Название задачи не может быть пустым")
    private String name;

    @Size(min = 10, message = "Минимальная длинна описания задачи: 10 символов")
    @Column(nullable = false)
    @NotNull(message = "Название задачи не может быть пустым")
    @NotEmpty(message = "Описание задачи не может быть пустым")
    @NotBlank(message = "Описание задачи не может быть пустым")
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.NEW;

    @Column(name = "\"start\"", columnDefinition = "TIMESTAMP")
    @Setter
    private LocalDateTime start;

    @Column(name = "\"end\"", columnDefinition = "TIMESTAMP")
    @Setter
    private LocalDateTime end;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
