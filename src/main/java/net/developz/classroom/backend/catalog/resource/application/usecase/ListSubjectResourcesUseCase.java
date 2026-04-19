package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListSubjectResourcesUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;

    public List<Resource> execute(String subjectId) {
        return resourceRepositoryPort.findBySubjectId(subjectId);
    }
}
