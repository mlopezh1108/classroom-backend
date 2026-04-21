package net.developz.classroom.backend.academic.advisory.application.port;

import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdvisoryRepositoryPort extends RepositoryPort<Advisory, String> {
    List<Advisory> findByEnrollmentId(String enrollmentId);
    Page<Advisory> findByEnrollmentId(String enrollmentId, Pageable pageable);
}
