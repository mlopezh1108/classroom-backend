package net.developz.classroom.backend.catalog.resource.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.ResourceEntity;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.repository.ResourceRepository;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRepositoryAdapter extends JpaRepositoryAdapter<Resource, ResourceEntity, String, ResourceRepository> implements ResourceRepositoryPort {

    private final ResourceMapper resourceMapper;

    public ResourceRepositoryAdapter(ResourceRepository repository, ResourceMapper resourceMapper) {
        super(repository);
        this.resourceMapper = resourceMapper;
    }

    @Override
    protected Resource toModel(ResourceEntity entity) {
        return resourceMapper.toModel(entity);
    }

    @Override
    protected ResourceEntity toEntity(Resource model) {
        return resourceMapper.toEntity(model);
    }

    @Override
    public List<Resource> findBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId).stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public PaginatedResult<Resource> findBySubjectId(String subjectId, PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<ResourceEntity> page = repository.findBySubjectId(subjectId, pageable);
        
        return new PaginatedResult<>(
                page.getContent().stream().map(this::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
