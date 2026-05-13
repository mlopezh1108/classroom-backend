package net.developz.classroom.backend.catalog.period.application.port;

import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;

public interface PeriodRepositoryPort extends RepositoryPort<Period, String> {
    PaginatedResult<Period> findAllSorted(PaginationCriteria criteria);
}
