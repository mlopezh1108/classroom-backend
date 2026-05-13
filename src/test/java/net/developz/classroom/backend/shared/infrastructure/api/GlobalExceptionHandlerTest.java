package net.developz.classroom.backend.shared.infrastructure.api;

import net.developz.classroom.backend.shared.application.exception.EntityAlreadyExistsException;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Not found", GlobalExceptionHandlerTest.class,
                Object.class);

        ProblemDetail result = handler.handleEntityNotFoundException(ex);

        assertThat(result.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(result.getDetail()).isEqualTo("Not found");
        assertThat(result.getTitle()).isEqualTo("Entity Not Found");
        assertThat(result.getProperties()).containsKey("timestamp");
        assertThat(result.getProperties()).containsEntry("useCase", "GlobalExceptionHandlerTest");
    }

    @Test
    void shouldHandleEntityAlreadyExistsException() {
        EntityAlreadyExistsException ex = new EntityAlreadyExistsException("Already exists",
                GlobalExceptionHandlerTest.class, Object.class);

        ProblemDetail result = handler.handleEntityAlreadyExistsException(ex);

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(result.getDetail()).isEqualTo("Already exists");
        assertThat(result.getTitle()).isEqualTo("Entity Already Exists");
        assertThat(result.getProperties()).containsKey("timestamp");
    }
}
