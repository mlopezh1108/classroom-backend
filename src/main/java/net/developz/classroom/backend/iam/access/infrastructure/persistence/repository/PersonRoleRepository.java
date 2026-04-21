package net.developz.classroom.backend.iam.access.infrastructure.persistence.repository;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRoleEntity, String> {
    List<PersonRoleEntity> findByPersonId(String personId);
}
