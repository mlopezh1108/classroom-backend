package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class UpdateResourceUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;

    public Resource execute(String resourceId, UpdateResourceRequest request) {
        Resource resource = resourceRepositoryPort.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource not found", UpdateResourceUseCase.class, Resource.class));

        if (request.title() != null) {
            resource.setTitle(request.title());
        }
        if (request.contentUrl() != null) {
            resource.setContentUrl(request.contentUrl());
        }

        return resourceRepositoryPort.save(resource);
    }
}
