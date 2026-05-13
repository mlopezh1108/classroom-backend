package net.developz.classroom.backend.catalog.period.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.catalog.period.infrastructure.mapper.PeriodMapper;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.PeriodEntity;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.repository.PeriodRepository;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PaginatedResult<Period> findAllSorted(PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<PeriodEntity> page = repository.findAllByOrderByCreatedAtDesc(pageable);
        
        return new PaginatedResult<>(
                page.getContent().stream().map(this::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
