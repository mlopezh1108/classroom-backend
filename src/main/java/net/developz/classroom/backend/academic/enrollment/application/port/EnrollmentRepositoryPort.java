package net.developz.classroom.backend.academic.enrollment.application.port;

import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

public interface EnrollmentRepositoryPort extends RepositoryPort<Enrollment, String> {
    PaginatedResult<Enrollment> findByCourseId(String courseId, PaginationCriteria criteria);
    List<Enrollment> findByCourseId(String courseId);
}
