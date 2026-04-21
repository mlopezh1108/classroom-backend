package net.developz.classroom.backend.academic.assessment.application.port;

import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamAttemptRepositoryPort extends RepositoryPort<ExamAttempt, String> {
    List<ExamAttempt> findByEnrollmentId(String enrollmentId);
    Page<ExamAttempt> findByEnrollmentId(String enrollmentId, Pageable pageable);
}
