package net.developz.classroom.backend.catalog.period.application.port;

import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

public interface PeriodRepositoryPort extends RepositoryPort<Period, String> {
}
