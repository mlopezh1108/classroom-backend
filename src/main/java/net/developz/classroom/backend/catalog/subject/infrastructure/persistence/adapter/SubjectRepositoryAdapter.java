package net.developz.classroom.backend.catalog.subject.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.catalog.subject.infrastructure.mapper.SubjectMapper;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.SubjectEntity;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.repository.SubjectRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

/**
 * Persistence adapter for the {@link Subject} entity.
 * Inherits generic CRUD operations from {@link JpaRepositoryAdapter}
 * and implements domain-specific queries.
 */
@Component
public class SubjectRepositoryAdapter extends JpaRepositoryAdapter<Subject, SubjectEntity, String, SubjectRepository> implements SubjectRepositoryPort {

    private final SubjectMapper subjectMapper;

    public SubjectRepositoryAdapter(SubjectRepository repository, SubjectMapper subjectMapper) {
        super(repository);
        this.subjectMapper = subjectMapper;
    }

    @Override
    protected Subject toModel(SubjectEntity entity) {
        return subjectMapper.toModel(entity);
    }

    @Override
    protected SubjectEntity toEntity(Subject model) {
        return subjectMapper.toEntity(model);
    }

    @Override
    public boolean existsBySubjectCode(String subjectCode) {
        return repository.existsBySubjectCode(subjectCode);
    }
}
