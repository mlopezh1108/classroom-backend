package net.developz.classroom.backend.iam.user.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.user.application.port.PersonRepositoryPort;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Person;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.repository.PersonRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonRepositoryAdapter extends JpaRepositoryAdapter<Person, String, PersonRepository>
        implements PersonRepositoryPort {

    public PersonRepositoryAdapter(PersonRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}



