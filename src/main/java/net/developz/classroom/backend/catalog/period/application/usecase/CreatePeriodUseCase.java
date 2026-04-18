package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class CreatePeriodUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    public Period execute(Period period) { return periodRepositoryPort.save(period); }
}
