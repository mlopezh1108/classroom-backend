package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCourseRosterUseCase {
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;

    public PaginatedResult<Enrollment> execute(String courseId, PaginationCriteria criteria) {
        return enrollmentRepositoryPort.findByCourseId(courseId, criteria);
    }

    public List<Enrollment> execute(String courseId) {
        return enrollmentRepositoryPort.findByCourseId(courseId);
    }
}
