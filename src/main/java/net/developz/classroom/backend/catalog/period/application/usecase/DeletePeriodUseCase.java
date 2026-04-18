package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class DeletePeriodUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    public void execute(String id) { periodRepositoryPort.deleteById(id); }
}
