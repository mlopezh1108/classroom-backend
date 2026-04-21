package net.developz.classroom.backend.iam.access.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.access.application.port.PersonRoleRepositoryPort;
import net.developz.classroom.backend.iam.access.domain.model.PersonRole;
import net.developz.classroom.backend.iam.access.infrastructure.mapper.AccessMapper;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonRoleEntity;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.repository.PersonRoleRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonRoleRepositoryAdapter
        extends JpaRepositoryAdapter<PersonRole, PersonRoleEntity, String, PersonRoleRepository>
        implements PersonRoleRepositoryPort {

    private final AccessMapper accessMapper;

    public PersonRoleRepositoryAdapter(PersonRoleRepository repository, AccessMapper accessMapper) {
        super(repository);
        this.accessMapper = accessMapper;
    }

    @Override
    protected PersonRole toModel(PersonRoleEntity entity) {
        return accessMapper.toModel(entity);
    }

    @Override
    protected PersonRoleEntity toEntity(PersonRole model) {
        return accessMapper.toEntity(model);
    }

    @Override
    public List<PersonRole> findByPersonId(String personId) {
        return repository.findByPersonId(personId).stream()
                .map(accessMapper::toModel)
                .toList();
    }
}
