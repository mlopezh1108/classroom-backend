package net.developz.classroom.backend.academic.course.application.usecase;

import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCourseByIdUseCaseTest {

    @Mock
    private CourseRepositoryPort repositoryPort;

    @InjectMocks
    private FindCourseByIdUseCase useCase;

    @Test
    void shouldFindCourseById() {
        String courseId = "course-123";
        Course course = new Course();
        when(repositoryPort.findById(courseId)).thenReturn(Optional.of(course));

        Course result = useCase.execute(courseId);

        assertThat(result).isEqualTo(course);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        String courseId = "unknown";
        when(repositoryPort.findById(courseId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(courseId))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
