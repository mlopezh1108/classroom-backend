package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisoryRepository extends JpaRepository<Advisory, String> {
    List<Advisory> findByEnrollment_Id(String enrollmentId);
}
