package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.Group;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllGroupsUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    public List<Group> execute() { return groupRepositoryPort.findAll(); }
}
