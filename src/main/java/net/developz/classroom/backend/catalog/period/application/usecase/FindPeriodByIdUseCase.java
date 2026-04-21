package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class FindPeriodByIdUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    public Period execute(String id) {
        return periodRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Period with id " + id + " not found", this.getClass(), Period.class));
    }
}
