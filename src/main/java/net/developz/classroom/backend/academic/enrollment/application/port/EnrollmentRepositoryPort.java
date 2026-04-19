package net.developz.classroom.backend.academic.enrollment.application.port;

import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface EnrollmentRepositoryPort extends RepositoryPort<Enrollment, String> {
    List<Enrollment> findByCourseId(String courseId);
    List<Enrollment> findByStudentId(String studentId);
}
