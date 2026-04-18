package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
    List<Exam> findBySubjectId(String subjectId);
}


