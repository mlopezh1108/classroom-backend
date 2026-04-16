package net.developz.classroom.backend.catalog.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Period;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, String> {
    Optional<Period> findByPeriodCode(String periodCode);
}
