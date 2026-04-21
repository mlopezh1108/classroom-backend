package net.developz.classroom.backend.iam.user.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.user.application.port.PersonRepositoryPort;
import net.developz.classroom.backend.iam.user.domain.model.Person;
import net.developz.classroom.backend.iam.user.infrastructure.mapper.UserMapper;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.PersonEntity;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.repository.PersonRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonRepositoryAdapter
        extends JpaRepositoryAdapter<Person, PersonEntity, String, PersonRepository>
        implements PersonRepositoryPort {

    private final UserMapper userMapper;

    public PersonRepositoryAdapter(PersonRepository repository, UserMapper userMapper) {
        super(repository);
        this.userMapper = userMapper;
    }

    @Override
    protected Person toModel(PersonEntity entity) {
        return userMapper.toModel(entity);
    }

    @Override
    protected PersonEntity toEntity(Person model) {
        return userMapper.toEntity(model);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email).map(userMapper::toModel);
    }
}
