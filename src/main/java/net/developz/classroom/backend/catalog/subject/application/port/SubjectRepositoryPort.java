package net.developz.classroom.backend.catalog.subject.application.port;

import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

/**
 * Output port specific to the {@link Subject} aggregate.
 * Extends {@link RepositoryPort} with subject domain-specific queries.
 */
public interface SubjectRepositoryPort extends RepositoryPort<Subject, String> {

    boolean existsBySubjectCode(String subjectCode);
}

