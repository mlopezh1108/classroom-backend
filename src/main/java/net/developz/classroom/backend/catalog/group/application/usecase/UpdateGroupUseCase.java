package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class UpdateGroupUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    public Group execute(Group group) { return groupRepositoryPort.save(group); }
}
