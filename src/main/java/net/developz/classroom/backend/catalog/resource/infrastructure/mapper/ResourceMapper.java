package net.developz.classroom.backend.catalog.resource.infrastructure.mapper;

import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    @Mapping(target = "subjectId", source = "subject.id")
    ResourceDTO toDTO(Resource resource);

    @Mapping(target = "subject", ignore = true)
    Resource toEntity(ResourceDTO resourceDTO);
}
