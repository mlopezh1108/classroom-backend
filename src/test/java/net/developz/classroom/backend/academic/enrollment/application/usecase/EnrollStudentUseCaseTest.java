package net.developz.classroom.backend.academic.enrollment.application.usecase;

import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentEventPublisherPort;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollStudentUseCaseTest {

    @Mock
    private EnrollmentRepositoryPort repositoryPort;

    @Mock
    private EnrollmentEventPublisherPort eventPublisherPort;

    @InjectMocks
    private EnrollStudentUseCase useCase;

    @Test
    void shouldEnrollStudentAndPublishEvent() {
        Enrollment enrollment = new Enrollment();
        when(repositoryPort.save(enrollment)).thenReturn(enrollment);

        Enrollment result = useCase.execute(enrollment);

        assertThat(result).isEqualTo(enrollment);
        verify(repositoryPort).save(enrollment);
        verify(eventPublisherPort).publishEnrollmentCreated(enrollment);
    }
}
