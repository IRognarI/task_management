package ru.visitor.task_management.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.visitor.task_management.exception.NotFoundException;
import ru.visitor.task_management.exception.ValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ErrorHandler errorHandler;

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandler ConstraintViolationExceptionHandler(final ConstraintViolationException e) {

        String description = e.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("Не известная ошибка валидации");

        errorHandler = new ErrorHandler("Ошибка валидации", description);

        log.error("{} - {}", errorHandler.getTitle(), errorHandler.getDescription());

        return errorHandler;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandler ValidationExceptionHandler(final ValidationException e) {

        errorHandler = new ErrorHandler("Не допустимое значение", e.getMessage());

        log.error("{} - {}", errorHandler.getTitle(), errorHandler.getDescription());

        return errorHandler;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorHandler NotFoundExceptionHandler(final NotFoundException e) {

        errorHandler = new ErrorHandler("Ошибка поиска", e.getMessage());

        log.error("{} - {}", errorHandler.getTitle(), errorHandler.getDescription());

        return errorHandler;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    public ErrorHandler HttpRequestMethodNotSupportedExceptionHandler(final HttpRequestMethodNotSupportedException e) {

        errorHandler = new ErrorHandler("Не поддерживаемый запрос", e.getMessage());

        log.error("{} - {}", errorHandler.getTitle(), errorHandler.getDescription());

        return errorHandler;
    }
}
