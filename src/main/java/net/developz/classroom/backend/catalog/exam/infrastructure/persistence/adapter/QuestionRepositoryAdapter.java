package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.exam.application.port.QuestionRepositoryPort;
import net.developz.classroom.backend.catalog.exam.domain.model.Question;
import net.developz.classroom.backend.catalog.exam.infrastructure.mapper.ExamMapper;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionEntity;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository.QuestionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class QuestionRepositoryAdapter
        extends JpaRepositoryAdapter<Question, QuestionEntity, String, QuestionRepository>
        implements QuestionRepositoryPort {

    private final ExamMapper examMapper;

    public QuestionRepositoryAdapter(QuestionRepository repository, ExamMapper examMapper) {
        super(repository);
        this.examMapper = examMapper;
    }

    @Override
    protected Question toModel(QuestionEntity entity) {
        return examMapper.toModel(entity);
    }

    @Override
    protected QuestionEntity toEntity(Question model) {
        return examMapper.toEntity(model);
    }
}
