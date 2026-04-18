package net.developz.classroom.backend.academic.course.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class CreateCourseUseCase {
    private final CourseRepositoryPort courseRepositoryPort;

    public Course execute(Course course) {
        // Here you could add validation if needed (e.g. check if subject/teacher/period exist via their ports)
        return courseRepositoryPort.save(course);
    }
}
