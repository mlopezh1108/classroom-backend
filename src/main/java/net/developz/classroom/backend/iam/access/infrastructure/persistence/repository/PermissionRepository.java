package net.developz.classroom.backend.iam.access.infrastructure.persistence.repository;

import net.developz.classroom.backend.iam.access.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {
    Optional<PermissionEntity> findByPermissionName(String permissionName);
}
