package net.developz.classroom.backend.catalog.period.application.port;

import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeriodRepositoryPort extends RepositoryPort<Period, String> {
    Page<Period> findAllSorted(Pageable pageable);
}
