package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListSubjectResourcesUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;

    public Page<Resource> execute(String subjectId, Pageable pageable) {
        return resourceRepositoryPort.findBySubjectId(subjectId, pageable);
    }

    public List<Resource> execute(String subjectId) {
        return resourceRepositoryPort.findBySubjectId(subjectId);
    }
}
