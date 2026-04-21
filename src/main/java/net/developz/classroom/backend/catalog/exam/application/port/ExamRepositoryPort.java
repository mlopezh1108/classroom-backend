package net.developz.classroom.backend.catalog.exam.application.port;

import net.developz.classroom.backend.catalog.exam.domain.model.Exam;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface ExamRepositoryPort extends RepositoryPort<Exam, String> {
    List<Exam> findBySubjectId(String subjectId);
}
