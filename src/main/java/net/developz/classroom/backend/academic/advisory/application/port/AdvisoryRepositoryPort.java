package net.developz.classroom.backend.academic.advisory.application.port;

import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

public interface AdvisoryRepositoryPort extends RepositoryPort<Advisory, String> {
    List<Advisory> findByEnrollmentId(String enrollmentId);
    PaginatedResult<Advisory> findByEnrollmentId(String enrollmentId, PaginationCriteria criteria);
}
