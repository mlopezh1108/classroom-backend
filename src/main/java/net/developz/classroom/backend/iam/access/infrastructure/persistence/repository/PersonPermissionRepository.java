package net.developz.classroom.backend.iam.access.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PersonPermission;

import java.util.List;

@Repository
public interface PersonPermissionRepository extends JpaRepository<PersonPermission, String> {
    List<PersonPermission> findByPersonId(String personId);

    List<PersonPermission> findByPersonIdAndActiveTrue(String personId);
}
