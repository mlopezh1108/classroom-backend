package net.developz.classroom.backend.catalog.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    Optional<Group> findByGroupCode(String groupCode);
}
