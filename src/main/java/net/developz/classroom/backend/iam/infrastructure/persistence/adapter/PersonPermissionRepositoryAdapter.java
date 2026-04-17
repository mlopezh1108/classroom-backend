package net.developz.classroom.backend.iam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.application.port.PersonPermissionRepositoryPort;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.PersonPermission;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PersonPermissionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonPermissionRepositoryAdapter extends JpaRepositoryAdapter<PersonPermission, String, PersonPermissionRepository>
        implements PersonPermissionRepositoryPort {

    public PersonPermissionRepositoryAdapter(PersonPermissionRepository repository) {
        super(repository);
    }

    @Override
    public List<PersonPermission> findByPersonIdAndActiveTrue(String personId) {
        return repository.findByPerson_IdAndActiveTrue(personId);
    }
}
