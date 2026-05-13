package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListSubjectResourcesUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;

    public PaginatedResult<Resource> execute(String subjectId, PaginationCriteria criteria) {
        return resourceRepositoryPort.findBySubjectId(subjectId, criteria);
    }

    public List<Resource> execute(String subjectId) {
        return resourceRepositoryPort.findBySubjectId(subjectId);
    }
}
