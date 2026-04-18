package net.developz.classroom.backend.catalog.period.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.repository.PeriodRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class PeriodRepositoryAdapter
        extends JpaRepositoryAdapter<Period, String, PeriodRepository>
        implements PeriodRepositoryPort {

    public PeriodRepositoryAdapter(PeriodRepository repository) {
        super(repository);
    }
}
