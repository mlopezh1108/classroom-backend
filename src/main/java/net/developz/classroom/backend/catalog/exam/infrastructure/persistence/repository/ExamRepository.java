package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.ExamEntity;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, String> {
    List<ExamEntity> findBySubjectId(String subjectId);
}


