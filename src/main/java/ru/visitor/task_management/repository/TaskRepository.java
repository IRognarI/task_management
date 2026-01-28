package ru.visitor.task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.visitor.task_management.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
