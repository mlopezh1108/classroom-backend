package net.developz.classroom.backend.catalog.period.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.PeriodEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<PeriodEntity, String> {
    Optional<PeriodEntity> findByPeriodCode(String periodCode);
    Page<PeriodEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
