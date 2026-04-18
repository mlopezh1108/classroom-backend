package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllPeriodsUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    public List<Period> execute() { return periodRepositoryPort.findAll(); }
}
