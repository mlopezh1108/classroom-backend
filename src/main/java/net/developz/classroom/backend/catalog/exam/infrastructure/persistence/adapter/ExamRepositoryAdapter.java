package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.domain.model.Exam;
import net.developz.classroom.backend.catalog.exam.infrastructure.mapper.ExamMapper;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.ExamEntity;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository.ExamRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamRepositoryAdapter
        extends JpaRepositoryAdapter<Exam, ExamEntity, String, ExamRepository>
        implements ExamRepositoryPort {

    private final ExamMapper examMapper;

    public ExamRepositoryAdapter(ExamRepository repository, ExamMapper examMapper) {
        super(repository);
        this.examMapper = examMapper;
    }

    @Override
    protected Exam toModel(ExamEntity entity) {
        return examMapper.toModel(entity);
    }

    @Override
    protected ExamEntity toEntity(Exam model) {
        return examMapper.toEntity(model);
    }

    @Override
    public List<Exam> findBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId).stream()
                .map(this::toModel)
                .toList();
    }
}
