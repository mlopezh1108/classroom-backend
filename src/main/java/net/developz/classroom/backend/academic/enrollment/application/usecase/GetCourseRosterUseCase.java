package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCourseRosterUseCase {
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;

    public List<Enrollment> execute(String courseId) {
        return enrollmentRepositoryPort.findByCourseId(courseId);
    }
}
