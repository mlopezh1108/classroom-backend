package net.developz.classroom.backend.iam.access.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.access.application.port.PersonPermissionRepositoryPort;
import net.developz.classroom.backend.iam.access.domain.model.PersonPermission;
import net.developz.classroom.backend.iam.access.infrastructure.mapper.AccessMapper;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonPermissionEntity;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.repository.PersonPermissionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonPermissionRepositoryAdapter
        extends JpaRepositoryAdapter<PersonPermission, PersonPermissionEntity, String, PersonPermissionRepository>
        implements PersonPermissionRepositoryPort {

    private final AccessMapper accessMapper;

    public PersonPermissionRepositoryAdapter(PersonPermissionRepository repository, AccessMapper accessMapper) {
        super(repository);
        this.accessMapper = accessMapper;
    }

    @Override
    protected PersonPermission toModel(PersonPermissionEntity entity) {
        return accessMapper.toModel(entity);
    }

    @Override
    protected PersonPermissionEntity toEntity(PersonPermission model) {
        return accessMapper.toEntity(model);
    }

    @Override
    public List<PersonPermission> findByPersonId(String personId) {
        return repository.findByPersonId(personId).stream()
                .map(accessMapper::toModel)
                .toList();
    }
}
