package net.developz.classroom.backend.catalog.group.application.port;

import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface GroupRepositoryPort extends RepositoryPort<Group, String> {
    Optional<Group> findByGroupCode(String groupCode);
}
