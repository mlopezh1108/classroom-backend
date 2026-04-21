package net.developz.classroom.backend.catalog.period.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.catalog.period.infrastructure.mapper.PeriodMapper;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.PeriodEntity;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.repository.PeriodRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PeriodRepositoryAdapter
        extends JpaRepositoryAdapter<Period, PeriodEntity, String, PeriodRepository>
        implements PeriodRepositoryPort {

    private final PeriodMapper periodMapper;

    public PeriodRepositoryAdapter(PeriodRepository repository, PeriodMapper periodMapper) {
        super(repository);
        this.periodMapper = periodMapper;
    }

    @Override
    protected Period toModel(PeriodEntity entity) {
        return periodMapper.toModel(entity);
    }

    @Override
    protected PeriodEntity toEntity(Period model) {
        return periodMapper.toEntity(model);
    }

    @Override
    public Page<Period> findAllSorted(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(pageable).map(this::toModel);
    }
}
