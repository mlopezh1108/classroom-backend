package net.developz.classroom.backend.catalog.group.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import net.developz.classroom.backend.catalog.group.application.dto.GroupResponse;
import net.developz.classroom.backend.catalog.group.application.dto.CreateGroupRequest;
import net.developz.classroom.backend.catalog.group.application.dto.UpdateGroupRequest;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.GroupEntity;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    // Entity <-> Domain
    Group toModel(GroupEntity entity);
    GroupEntity toEntity(Group model);

    // Domain <-> DTO
    GroupResponse toDto(Group model);
    Group toModel(CreateGroupRequest request);
    void updateModelFromRequest(UpdateGroupRequest request, @MappingTarget Group model);
}
