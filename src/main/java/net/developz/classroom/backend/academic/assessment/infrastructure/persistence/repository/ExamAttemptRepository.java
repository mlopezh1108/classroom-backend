package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttemptEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttemptEntity, String> {
    List<ExamAttemptEntity> findByEnrollmentId(String enrollmentId);
    Page<ExamAttemptEntity> findByEnrollmentId(String enrollmentId, Pageable pageable);
}
