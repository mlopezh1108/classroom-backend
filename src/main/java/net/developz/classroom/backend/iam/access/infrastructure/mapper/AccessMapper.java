package net.developz.classroom.backend.iam.access.infrastructure.mapper;

import net.developz.classroom.backend.iam.access.domain.model.*;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessMapper {

    // Role
    Role toModel(RoleEntity entity);
    RoleEntity toEntity(Role model);

    // Permission
    Permission toModel(PermissionEntity entity);
    PermissionEntity toEntity(Permission model);

    // PersonRole
    PersonRole toModel(PersonRoleEntity entity);
    PersonRoleEntity toEntity(PersonRole model);

    // PersonPermission
    PersonPermission toModel(PersonPermissionEntity entity);
    PersonPermissionEntity toEntity(PersonPermission model);
}
