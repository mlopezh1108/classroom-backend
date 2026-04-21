package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCourseRosterUseCase {
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;

    public Page<Enrollment> execute(String courseId, Pageable pageable) {
        return enrollmentRepositoryPort.findByCourseId(courseId, pageable);
    }

    public List<Enrollment> execute(String courseId) {
        return enrollmentRepositoryPort.findByCourseId(courseId);
    }
}
