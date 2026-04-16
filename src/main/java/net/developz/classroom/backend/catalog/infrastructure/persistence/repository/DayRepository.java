package net.developz.classroom.backend.catalog.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Day;

import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<Day, String> {
    Optional<Day> findByDayName(String dayName);
}
