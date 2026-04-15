package net.developz.classroom.backend.iam.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.infrastructure.persistence.entities.Permission;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    Optional<Permission> findByPermissionName(String permissionName);
}
