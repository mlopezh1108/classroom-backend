package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteGroupUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    public void execute(String id) { groupRepositoryPort.deleteById(id); }
}
