package net.developz.classroom.backend.catalog.subject.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.repository.SubjectRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

/**
 * Persistence adapter for the {@link Subject} entity.
 * Inherits generic CRUD operations from {@link JpaRepositoryAdapter}
 * and implements domain-specific queries.
 */
@Component
public class SubjectRepositoryAdapter
        extends JpaRepositoryAdapter<Subject, String, SubjectRepository>
        implements SubjectRepositoryPort {

    public SubjectRepositoryAdapter(SubjectRepository repository) {
        super(repository);
    }

    @Override
    public boolean existsBySubjectCode(String subjectCode) {
        return repository.existsBySubjectCode(subjectCode);
    }
}


