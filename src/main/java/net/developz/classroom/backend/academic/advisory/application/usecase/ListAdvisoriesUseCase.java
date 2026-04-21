package net.developz.classroom.backend.academic.advisory.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAdvisoriesUseCase {
    private final AdvisoryRepositoryPort advisoryRepositoryPort;

    public Page<Advisory> execute(String enrollmentId, Pageable pageable) {
        return advisoryRepositoryPort.findByEnrollmentId(enrollmentId, pageable);
    }

    public List<Advisory> execute(String enrollmentId) {
        return advisoryRepositoryPort.findByEnrollmentId(enrollmentId);
    }
}
