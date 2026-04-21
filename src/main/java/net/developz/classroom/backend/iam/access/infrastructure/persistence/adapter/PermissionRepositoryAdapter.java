package net.developz.classroom.backend.iam.access.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.access.application.port.PermissionRepositoryPort;
import net.developz.classroom.backend.iam.access.domain.model.Permission;
import net.developz.classroom.backend.iam.access.infrastructure.mapper.AccessMapper;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PermissionEntity;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.repository.PermissionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PermissionRepositoryAdapter
        extends JpaRepositoryAdapter<Permission, PermissionEntity, String, PermissionRepository>
        implements PermissionRepositoryPort {

    private final AccessMapper accessMapper;

    public PermissionRepositoryAdapter(PermissionRepository repository, AccessMapper accessMapper) {
        super(repository);
        this.accessMapper = accessMapper;
    }

    @Override
    protected Permission toModel(PermissionEntity entity) {
        return accessMapper.toModel(entity);
    }

    @Override
    protected PermissionEntity toEntity(Permission model) {
        return accessMapper.toEntity(model);
    }

    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
        return repository.findByPermissionName(permissionName).map(accessMapper::toModel);
    }
}
