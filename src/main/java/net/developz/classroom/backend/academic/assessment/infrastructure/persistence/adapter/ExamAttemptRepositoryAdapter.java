package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.mapper.AssessmentMapper;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttemptEntity;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository.ExamAttemptRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamAttemptRepositoryAdapter
        extends JpaRepositoryAdapter<ExamAttempt, ExamAttemptEntity, String, ExamAttemptRepository>
        implements ExamAttemptRepositoryPort {

    private final AssessmentMapper assessmentMapper;

    public ExamAttemptRepositoryAdapter(ExamAttemptRepository repository, AssessmentMapper assessmentMapper) {
        super(repository);
        this.assessmentMapper = assessmentMapper;
    }

    @Override
    protected ExamAttempt toModel(ExamAttemptEntity entity) {
        return assessmentMapper.toModel(entity);
    }

    @Override
    protected ExamAttemptEntity toEntity(ExamAttempt model) {
        return assessmentMapper.toEntity(model);
    }

    @Override
    public List<ExamAttempt> findByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId).stream()
                .map(assessmentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExamAttempt> findByEnrollmentId(String enrollmentId, Pageable pageable) {
        return repository.findByEnrollmentId(enrollmentId, pageable)
                .map(assessmentMapper::toModel);
    }
}
