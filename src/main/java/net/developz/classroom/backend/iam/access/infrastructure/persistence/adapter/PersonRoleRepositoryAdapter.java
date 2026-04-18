package net.developz.classroom.backend.iam.access.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.access.application.port.PersonRoleRepositoryPort;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonRole;
import net.developz.classroom.backend.iam.access.infrastructure.persistence.repository.PersonRoleRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonRoleRepositoryAdapter extends JpaRepositoryAdapter<PersonRole, String, PersonRoleRepository>
        implements PersonRoleRepositoryPort {

    public PersonRoleRepositoryAdapter(PersonRoleRepository repository) {
        super(repository);
    }

    @Override
    public List<PersonRole> findByPersonIdAndActiveTrue(String personId) {
        return repository.findByPersonIdAndActiveTrue(personId);
    }
}



