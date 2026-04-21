package net.developz.classroom.backend.iam.access.infrastructure.persistence.repository;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonPermissionRepository extends JpaRepository<PersonPermissionEntity, String> {
    List<PersonPermissionEntity> findByPersonId(String personId);
}
