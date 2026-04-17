package net.developz.classroom.backend.iam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.application.port.PermissionRepositoryPort;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Permission;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PermissionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PermissionRepositoryAdapter extends JpaRepositoryAdapter<Permission, String, PermissionRepository>
        implements PermissionRepositoryPort {

    public PermissionRepositoryAdapter(PermissionRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
        return repository.findByPermissionName(permissionName);
    }
}
