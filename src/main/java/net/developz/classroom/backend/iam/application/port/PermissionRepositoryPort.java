package net.developz.classroom.backend.iam.application.port;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Permission;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface PermissionRepositoryPort extends RepositoryPort<Permission, String> {
    Optional<Permission> findByPermissionName(String permissionName);
}
