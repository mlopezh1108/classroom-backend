package net.developz.classroom.backend.iam.access.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.access.application.port.RoleRepositoryPort;
import net.developz.classroom.backend.iam.access.domain.model.Role;
import net.developz.classroom.backend.iam.access.infrastructure.mapper.AccessMapper;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.RoleEntity;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.repository.RoleRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryAdapter
        extends JpaRepositoryAdapter<Role, RoleEntity, String, RoleRepository>
        implements RoleRepositoryPort {

    private final AccessMapper accessMapper;

    public RoleRepositoryAdapter(RoleRepository repository, AccessMapper accessMapper) {
        super(repository);
        this.accessMapper = accessMapper;
    }

    @Override
    protected Role toModel(RoleEntity entity) {
        return accessMapper.toModel(entity);
    }

    @Override
    protected RoleEntity toEntity(Role model) {
        return accessMapper.toEntity(model);
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return repository.findByRoleName(roleName).map(accessMapper::toModel);
    }
}
