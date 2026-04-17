package net.developz.classroom.backend.iam.application.port;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.PersonRole;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface PersonRoleRepositoryPort extends RepositoryPort<PersonRole, String> {
    List<PersonRole> findByPersonIdAndActiveTrue(String personId);
}
