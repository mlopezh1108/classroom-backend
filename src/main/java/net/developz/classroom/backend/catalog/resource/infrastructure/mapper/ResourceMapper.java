package net.developz.classroom.backend.catalog.resource.infrastructure.mapper;

import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    ResourceDTO toDTO(Resource resource);

    Resource toEntity(ResourceDTO resourceDTO);
}
