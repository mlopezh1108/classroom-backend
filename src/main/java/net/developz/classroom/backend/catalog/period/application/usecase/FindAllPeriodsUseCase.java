package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllPeriodsUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    
    public PaginatedResult<Period> execute(PaginationCriteria criteria) {
        return periodRepositoryPort.findAllSorted(criteria);
    }

    public List<Period> execute() { 
        return periodRepositoryPort.findAll(); 
    }
}
