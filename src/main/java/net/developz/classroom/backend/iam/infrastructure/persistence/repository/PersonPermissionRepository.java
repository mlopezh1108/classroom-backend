package net.developz.classroom.backend.iam.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.PersonPermission;

import java.util.List;

@Repository
public interface PersonPermissionRepository extends JpaRepository<PersonPermission, String> {
    List<PersonPermission> findByPerson_Id(String personId);

    List<PersonPermission> findByPerson_IdAndActiveTrue(String personId);
}
