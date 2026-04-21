package net.developz.classroom.backend.iam.user.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.user.application.port.AdministratorRepositoryPort;
import net.developz.classroom.backend.iam.user.domain.model.Administrator;
import net.developz.classroom.backend.iam.user.infrastructure.mapper.UserMapper;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.AdministratorEntity;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.repository.AdministratorRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class AdministratorRepositoryAdapter
        extends JpaRepositoryAdapter<Administrator, AdministratorEntity, String, AdministratorRepository>
        implements AdministratorRepositoryPort {

    private final UserMapper userMapper;

    public AdministratorRepositoryAdapter(AdministratorRepository repository, UserMapper userMapper) {
        super(repository);
        this.userMapper = userMapper;
    }

    @Override
    protected Administrator toModel(AdministratorEntity entity) {
        return userMapper.toModel(entity);
    }

    @Override
    protected AdministratorEntity toEntity(Administrator model) {
        return userMapper.toEntity(model);
    }
}
