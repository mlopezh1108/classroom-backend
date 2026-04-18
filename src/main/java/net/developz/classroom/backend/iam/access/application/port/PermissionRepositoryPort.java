package net.developz.classroom.backend.iam.access.application.port;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.Permission;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface PermissionRepositoryPort extends RepositoryPort<Permission, String> {
    Optional<Permission> findByPermissionName(String permissionName);
}



