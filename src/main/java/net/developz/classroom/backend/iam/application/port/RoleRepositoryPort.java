package net.developz.classroom.backend.iam.application.port;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Role;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface RoleRepositoryPort extends RepositoryPort<Role, String> {
    Optional<Role> findByRoleName(String roleName);
}
