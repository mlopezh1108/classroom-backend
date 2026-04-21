package net.developz.classroom.backend.iam.access.application.port;

import net.developz.classroom.backend.iam.access.domain.model.PersonPermission;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface PersonPermissionRepositoryPort extends RepositoryPort<PersonPermission, String> {
    List<PersonPermission> findByPersonId(String personId);
}
