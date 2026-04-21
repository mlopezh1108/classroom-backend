package net.developz.classroom.backend.catalog.group.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllGroupsUseCase {
    private final GroupRepositoryPort groupRepositoryPort;
    
    public Page<Group> execute(Pageable pageable) { 
        return groupRepositoryPort.findAll(pageable); 
    }

    public List<Group> execute() { 
        return groupRepositoryPort.findAll(); 
    }
}
