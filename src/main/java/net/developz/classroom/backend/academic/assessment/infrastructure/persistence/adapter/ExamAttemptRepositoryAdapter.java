package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository.ExamAttemptRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamAttemptRepositoryAdapter extends JpaRepositoryAdapter<ExamAttempt, String, ExamAttemptRepository> implements ExamAttemptRepositoryPort {

    public ExamAttemptRepositoryAdapter(ExamAttemptRepository repository) {
        super(repository);
    }

    @Override
    public List<ExamAttempt> findByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId);
    }

    @Override
    public List<ExamAttempt> findByExamId(String examId) {
        return repository.findByExamId(examId);
    }
}
