package net.developz.classroom.backend.catalog.resource.application.port;

import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ResourceRepositoryPort extends RepositoryPort<Resource, String> {
    List<Resource> findBySubjectId(String subjectId);
    Page<Resource> findBySubjectId(String subjectId, Pageable pageable);
}
