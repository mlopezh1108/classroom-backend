package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository.ExamRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamRepositoryAdapter extends JpaRepositoryAdapter<Exam, String, ExamRepository> implements ExamRepositoryPort {

    public ExamRepositoryAdapter(ExamRepository repository) {
        super(repository);
    }

    @Override
    public List<Exam> findBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }
}
