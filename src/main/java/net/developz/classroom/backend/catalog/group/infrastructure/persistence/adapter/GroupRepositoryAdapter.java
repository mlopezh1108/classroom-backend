package net.developz.classroom.backend.catalog.group.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.group.application.port.GroupRepositoryPort;
import net.developz.classroom.backend.catalog.group.domain.model.Group;
import net.developz.classroom.backend.catalog.group.infrastructure.mapper.GroupMapper;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.GroupEntity;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.repository.GroupRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GroupRepositoryAdapter extends JpaRepositoryAdapter<Group, GroupEntity, String, GroupRepository> implements GroupRepositoryPort {

    private final GroupMapper groupMapper;

    public GroupRepositoryAdapter(GroupRepository repository, GroupMapper groupMapper) {
        super(repository);
        this.groupMapper = groupMapper;
    }

    @Override
    protected Group toModel(GroupEntity entity) {
        return groupMapper.toModel(entity);
    }

    @Override
    protected GroupEntity toEntity(Group model) {
        return groupMapper.toEntity(model);
    }

    @Override
    public Optional<Group> findByGroupCode(String groupCode) {
        return repository.findByGroupCode(groupCode).map(this::toModel);
    }
}
