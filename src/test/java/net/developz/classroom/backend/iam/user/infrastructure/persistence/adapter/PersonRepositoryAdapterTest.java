package net.developz.classroom.backend.iam.user.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Person;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersonRepositoryAdapter.class)
class PersonRepositoryAdapterTest {

    @Autowired
    private PersonRepositoryAdapter adapter;

    @Autowired
    private PersonRepository repository;

    @Test
    void shouldSaveAndFindByEmail() {
        Person person = new Person();
        person.setEmail("test@email.com");
        person.setPassword("pass");
        person.setFirstName("Test");
        person.setLastName("User");
        repository.save(person);

        Optional<Person> result = adapter.findByEmail("test@email.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@email.com");
    }
}
