package net.developz.classroom.backend.iam.user.application.port;

import net.developz.classroom.backend.iam.user.domain.model.Person;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.Optional;

public interface PersonRepositoryPort extends RepositoryPort<Person, String> {
    Optional<Person> findByEmail(String email);
}



