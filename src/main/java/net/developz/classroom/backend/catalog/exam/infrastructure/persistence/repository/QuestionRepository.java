package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionEntity;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {
    List<QuestionEntity> findByExamIdOrderByOrderIndexAsc(String examId);
}
