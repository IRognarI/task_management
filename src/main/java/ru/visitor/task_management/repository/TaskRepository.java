package ru.visitor.task_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.visitor.task_management.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM tasks LIMIT :limit", nativeQuery = true)
    List<Task> getAllAndLimit(@Param("limit") Integer limit);

}
