package net.developz.classroom.backend.academic.course.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListTeacherCoursesUseCase {
    private final CourseRepositoryPort courseRepositoryPort;

    public List<Course> execute(String teacherId) {
        return courseRepositoryPort.findByTeacherId(teacherId);
    }

    public PaginatedResult<Course> execute(String teacherId, PaginationCriteria criteria) {
        return courseRepositoryPort.findByTeacherId(teacherId, criteria);
    }
}
