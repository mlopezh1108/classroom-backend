package net.developz.classroom.backend.catalog.resource.infrastructure.mapper;

import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {
    // Entity <-> Domain
    Resource toModel(ResourceEntity entity);
    ResourceEntity toEntity(Resource model);

    // Domain <-> DTO
    ResourceDTO toDto(Resource model);
    Resource toModel(CreateResourceRequest request);
    void updateModelFromRequest(UpdateResourceRequest request, @MappingTarget Resource resource);
}
