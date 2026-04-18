package net.developz.classroom.backend.academic.course.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class FindCourseByIdUseCase {
    private final CourseRepositoryPort courseRepositoryPort;

    public Course execute(String id) {
        return courseRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course with id " + id + " not found", this.getClass(), Course.class));
    }
}
