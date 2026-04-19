package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class CreateResourceUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;
    private final SubjectRepositoryPort subjectRepositoryPort;

    public Resource execute(CreateResourceRequest request) {
        Subject subject = subjectRepositoryPort.findById(request.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found", CreateResourceUseCase.class, Subject.class));

        Resource resource = new Resource();
        resource.setTitle(request.title());
        resource.setResourceType(request.resourceType());
        resource.setContentUrl(request.contentUrl());
        resource.setSubject(subject);

        return resourceRepositoryPort.save(resource);
    }
}
