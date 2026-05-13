package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.mapper.AdvisoryMapper;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.AdvisoryEntity;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.repository.AdvisoryRepository;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvisoryRepositoryAdapter
        extends JpaRepositoryAdapter<Advisory, AdvisoryEntity, String, AdvisoryRepository>
        implements AdvisoryRepositoryPort {

    private final AdvisoryMapper advisoryMapper;

    public AdvisoryRepositoryAdapter(AdvisoryRepository repository, AdvisoryMapper advisoryMapper) {
        super(repository);
        this.advisoryMapper = advisoryMapper;
    }

    @Override
    protected Advisory toModel(AdvisoryEntity entity) {
        return advisoryMapper.toModel(entity);
    }

    @Override
    protected AdvisoryEntity toEntity(Advisory model) {
        return advisoryMapper.toEntity(model);
    }

    @Override
    public List<Advisory> findByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId).stream()
                .map(advisoryMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<Advisory> findByEnrollmentId(String enrollmentId, PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<AdvisoryEntity> page = repository.findByEnrollmentId(enrollmentId, pageable);
        
        return new PaginatedResult<>(
                page.getContent().stream().map(advisoryMapper::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
