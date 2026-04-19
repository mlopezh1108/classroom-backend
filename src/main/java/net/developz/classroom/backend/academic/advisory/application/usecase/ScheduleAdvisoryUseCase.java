package net.developz.classroom.backend.academic.advisory.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.enums.AdvisoryStatus;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class ScheduleAdvisoryUseCase {
    private final AdvisoryRepositoryPort advisoryRepositoryPort;

    public Advisory execute(Advisory advisory) {
        advisory.setStatus(AdvisoryStatus.SCHEDULED);
        return advisoryRepositoryPort.save(advisory);
    }
}
