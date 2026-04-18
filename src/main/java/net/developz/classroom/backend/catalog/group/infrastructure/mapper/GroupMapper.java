package net.developz.classroom.backend.catalog.group.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import net.developz.classroom.backend.catalog.group.application.dto.GroupResponse;
import net.developz.classroom.backend.catalog.group.application.dto.CreateGroupRequest;
import net.developz.classroom.backend.catalog.group.application.dto.UpdateGroupRequest;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupResponse toDto(Group group);
    Group toEntity(CreateGroupRequest request);
    void updateEntityFromRequest(UpdateGroupRequest request, @MappingTarget Group group);
}
