package net.developz.classroom.backend.academic.course.application.usecase;

import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCourseUseCaseTest {

    @Mock
    private CourseRepositoryPort repositoryPort;

    @InjectMocks
    private CreateCourseUseCase useCase;

    @Test
    void shouldCreateCourse() {
        Course course = new Course();
        when(repositoryPort.save(course)).thenReturn(course);

        Course result = useCase.execute(course);

        assertThat(result).isEqualTo(course);
        verify(repositoryPort).save(course);
    }
}
