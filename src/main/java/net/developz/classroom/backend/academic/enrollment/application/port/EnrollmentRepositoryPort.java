package net.developz.classroom.backend.academic.enrollment.application.port;

import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EnrollmentRepositoryPort extends RepositoryPort<Enrollment, String> {
    Page<Enrollment> findByCourseId(String courseId, Pageable pageable);
    List<Enrollment> findByCourseId(String courseId);
}
