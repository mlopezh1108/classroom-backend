package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllGroupsUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    
    public PaginatedResult<Group> execute(PaginationCriteria criteria) { 
        return groupRepositoryPort.findAll(criteria); 
    }

    public List<Group> execute() { 
        return groupRepositoryPort.findAll(); 
    }
}
