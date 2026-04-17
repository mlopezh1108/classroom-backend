package net.developz.classroom.backend.iam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.application.port.RoleRepositoryPort;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Role;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.RoleRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryAdapter extends JpaRepositoryAdapter<Role, String, RoleRepository>
        implements RoleRepositoryPort {

    public RoleRepositoryAdapter(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return repository.findByRoleName(roleName);
    }
}
