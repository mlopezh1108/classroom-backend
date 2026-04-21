package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.AttemptAnswerEntity;

import java.util.List;

@Repository
public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswerEntity, String> {
    List<AttemptAnswerEntity> findByExamAttemptId(String attemptId);
}


