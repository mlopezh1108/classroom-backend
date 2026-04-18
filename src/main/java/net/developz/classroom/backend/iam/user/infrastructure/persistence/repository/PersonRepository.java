package net.developz.classroom.backend.iam.user.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByEmail(String email);
}



