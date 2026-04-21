package net.developz.classroom.backend.iam.access.application.port;

import net.developz.classroom.backend.iam.access.domain.model.PersonRole;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface PersonRoleRepositoryPort extends RepositoryPort<PersonRole, String> {
    List<PersonRole> findByPersonId(String personId);
}
