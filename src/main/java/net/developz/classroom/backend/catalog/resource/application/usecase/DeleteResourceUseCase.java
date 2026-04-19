package net.developz.classroom.backend.catalog.resource.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteResourceUseCase {
    private final ResourceRepositoryPort resourceRepositoryPort;

    public void execute(String resourceId) {
        resourceRepositoryPort.deleteById(resourceId);
    }
}
