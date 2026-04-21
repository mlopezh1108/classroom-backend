package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class FindGroupByIdUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    public Group execute(String id) {
        return groupRepositoryPort.findById(id).orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " not found", this.getClass(), Group.class));
    }
}
