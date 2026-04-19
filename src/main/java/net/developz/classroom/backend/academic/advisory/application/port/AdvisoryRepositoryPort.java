package net.developz.classroom.backend.academic.advisory.application.port;

import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface AdvisoryRepositoryPort extends RepositoryPort<Advisory, String> {
    List<Advisory> findByEnrollmentId(String enrollmentId);
}
