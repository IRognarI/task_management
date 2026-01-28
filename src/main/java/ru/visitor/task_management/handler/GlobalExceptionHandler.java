package ru.visitor.task_management.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandler ConstraintViolationExceptionHandler(final ConstraintViolationException e) {

        String description = e.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Не известная ошибка валидации");

        ErrorHandler errorHandler = new ErrorHandler("Ошибка валидации", description);

        log.error("{} - {}", errorHandler.getTitle(), errorHandler.getDescription());

        return errorHandler;
    }
}
