package net.developz.classroom.backend.academic.enrollment.application.usecase;

import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCourseRosterUseCaseTest {

    @Mock
    private EnrollmentRepositoryPort repositoryPort;

    @InjectMocks
    private GetCourseRosterUseCase useCase;

    @Test
    void shouldGetCourseRoster() {
        String courseId = "course-123";
        List<Enrollment> roster = List.of(new Enrollment(), new Enrollment());
        when(repositoryPort.findByCourseId(courseId)).thenReturn(roster);

        List<Enrollment> result = useCase.execute(courseId);

        assertThat(result).hasSize(2).isEqualTo(roster);
    }
}
