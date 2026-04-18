package net.developz.classroom.backend.iam.access.application.port;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonPermission;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface PersonPermissionRepositoryPort extends RepositoryPort<PersonPermission, String> {
    List<PersonPermission> findByPersonIdAndActiveTrue(String personId);
}



