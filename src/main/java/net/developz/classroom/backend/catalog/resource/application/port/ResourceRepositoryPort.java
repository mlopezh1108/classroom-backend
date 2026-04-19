package net.developz.classroom.backend.catalog.resource.application.port;

import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface ResourceRepositoryPort extends RepositoryPort<Resource, String> {
    List<Resource> findBySubjectId(String subjectId);
}
