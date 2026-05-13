package net.developz.classroom.backend.academic.advisory.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAdvisoriesUseCase {
    private final AdvisoryRepositoryPort advisoryRepositoryPort;

    public PaginatedResult<Advisory> execute(String enrollmentId, PaginationCriteria criteria) {
        return advisoryRepositoryPort.findByEnrollmentId(enrollmentId, criteria);
    }

    public List<Advisory> execute(String enrollmentId) {
        return advisoryRepositoryPort.findByEnrollmentId(enrollmentId);
    }
}
