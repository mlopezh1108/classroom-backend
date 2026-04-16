package net.developz.classroom.backend.academic.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.academic.infrastructure.persistence.entity.ExamAttempt;

import java.util.List;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, String> {
    List<ExamAttempt> findByEnrollmentId(String enrollmentId);

    List<ExamAttempt> findByExamId(String examId);
}
