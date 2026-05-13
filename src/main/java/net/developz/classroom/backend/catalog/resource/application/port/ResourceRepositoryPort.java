package net.developz.classroom.backend.catalog.resource.application.port;

import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

public interface ResourceRepositoryPort extends RepositoryPort<Resource, String> {
    List<Resource> findBySubjectId(String subjectId);
    PaginatedResult<Resource> findBySubjectId(String subjectId, PaginationCriteria criteria);
}
