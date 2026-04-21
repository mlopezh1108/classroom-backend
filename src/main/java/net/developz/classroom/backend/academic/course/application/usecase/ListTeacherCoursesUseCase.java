package net.developz.classroom.backend.academic.course.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListTeacherCoursesUseCase {
    private final CourseRepositoryPort courseRepositoryPort;

    public List<Course> execute(String teacherId) {
        return courseRepositoryPort.findByTeacherId(teacherId);
    }

    public Page<Course> execute(String teacherId, Pageable pageable) {
        return courseRepositoryPort.findByTeacherId(teacherId, pageable);
    }
}
