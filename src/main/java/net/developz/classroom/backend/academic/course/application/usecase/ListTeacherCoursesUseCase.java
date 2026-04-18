package net.developz.classroom.backend.academic.course.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListTeacherCoursesUseCase {
    private final CourseRepositoryPort courseRepositoryPort;

    public List<Course> execute(String teacherId) {
        return courseRepositoryPort.findByTeacherId(teacherId);
    }
}
