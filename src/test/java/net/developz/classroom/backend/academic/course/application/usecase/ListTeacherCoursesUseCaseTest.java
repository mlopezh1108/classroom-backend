package net.developz.classroom.backend.academic.course.application.usecase;

import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListTeacherCoursesUseCaseTest {

    @Mock
    private CourseRepositoryPort repositoryPort;

    @InjectMocks
    private ListTeacherCoursesUseCase useCase;

    @Test
    void shouldListTeacherCourses() {
        String teacherId = "teacher-123";
        List<Course> courses = List.of(new Course(), new Course());
        when(repositoryPort.findByTeacherId(teacherId)).thenReturn(courses);

        List<Course> result = useCase.execute(teacherId);

        assertThat(result).hasSize(2).isEqualTo(courses);
    }
}
