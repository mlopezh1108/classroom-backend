package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class CreateResourceUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;
    private final SubjectRepositoryPort subjectRepositoryPort;
    private final ResourceMapper resourceMapper;

    public Resource execute(CreateResourceRequest request) {
        if (!subjectRepositoryPort.existsById(request.subjectId())) {
            throw new EntityNotFoundException("Subject not found: " + request.subjectId(), CreateResourceUseCase.class, null);
        }

        Resource resource = resourceMapper.toModel(request);
        return resourceRepositoryPort.save(resource);
    }
}
