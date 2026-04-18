package net.developz.classroom.backend.catalog.group.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.Group;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.repository.GroupRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class GroupRepositoryAdapter
        extends JpaRepositoryAdapter<Group, String, GroupRepository>
        implements GroupRepositoryPort {

    public GroupRepositoryAdapter(GroupRepository repository) {
        super(repository);
    }
}
