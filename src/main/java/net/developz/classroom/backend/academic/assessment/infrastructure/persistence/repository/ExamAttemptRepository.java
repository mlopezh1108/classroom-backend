package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, String> {
    List<ExamAttempt> findByEnrollmentId(String enrollmentId);
    List<ExamAttempt> findByExamId(String examId);
}
