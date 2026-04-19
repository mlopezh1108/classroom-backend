package net.developz.classroom.backend.academic.enrollment.application.usecase;

import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DropCourseUseCaseTest {

    @Mock
    private EnrollmentRepositoryPort repositoryPort;

    @InjectMocks
    private DropCourseUseCase useCase;

    @Test
    void shouldDropCourse() {
        String enrollmentId = "enroll-123";

        useCase.execute(enrollmentId);

        verify(repositoryPort).deleteById(enrollmentId);
    }
}
