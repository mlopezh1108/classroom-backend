package net.developz.classroom.backend.academic.assessment.application.port;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface ExamAttemptRepositoryPort extends RepositoryPort<ExamAttempt, String> {
    List<ExamAttempt> findByEnrollmentId(String enrollmentId);
    List<ExamAttempt> findByExamId(String examId);
}
