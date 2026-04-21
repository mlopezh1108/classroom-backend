package net.developz.classroom.backend.iam.access.application.port;

import net.developz.classroom.backend.iam.access.domain.model.Role;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface RoleRepositoryPort extends RepositoryPort<Role, String> {
    Optional<Role> findByRoleName(String roleName);
}
