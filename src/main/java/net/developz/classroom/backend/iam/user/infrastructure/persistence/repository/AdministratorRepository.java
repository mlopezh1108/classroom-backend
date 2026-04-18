package net.developz.classroom.backend.iam.user.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {
}



