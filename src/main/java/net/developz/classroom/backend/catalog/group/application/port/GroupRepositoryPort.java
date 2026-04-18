package net.developz.classroom.backend.catalog.group.application.port;

import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.Group;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

public interface GroupRepositoryPort extends RepositoryPort<Group, String> {
}
