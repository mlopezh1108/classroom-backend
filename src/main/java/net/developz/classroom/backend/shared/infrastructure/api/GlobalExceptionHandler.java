package net.developz.classroom.backend.shared.infrastructure.api;

import net.developz.classroom.backend.shared.application.exception.EntityAlreadyExistsException;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Entity Not Found");
        problemDetail.setType(URI.create("https://api.developz.net/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("useCase", ex.getUseCaseClass().getSimpleName());
        problemDetail.setProperty("entity", ex.getEntityClass().getSimpleName());
        return problemDetail;
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ProblemDetail handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Entity Already Exists");
        problemDetail.setType(URI.create("https://api.developz.net/errors/already-exists"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("useCase", ex.getUseCaseClass().getSimpleName());
        problemDetail.setProperty("entity", ex.getEntityClass().getSimpleName());
        return problemDetail;
    }
}
