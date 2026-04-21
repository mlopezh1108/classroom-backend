package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class UpdateResourceUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;
    private final ResourceMapper resourceMapper;

    public Resource execute(String resourceId, UpdateResourceRequest request) {
        Resource resource = resourceRepositoryPort.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource not found", UpdateResourceUseCase.class, Resource.class));

        resourceMapper.updateModelFromRequest(request, resource);

        return resourceRepositoryPort.save(resource);
    }
}
