package net.developz.classroom.backend.iam.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.PersonRole;

import java.util.List;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole, String> {
    List<PersonRole> findByPersonIdAndActiveTrue(String personId);
}
