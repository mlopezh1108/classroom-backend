package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.AdvisoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisoryRepository extends JpaRepository<AdvisoryEntity, String> {
    List<AdvisoryEntity> findByEnrollmentId(String enrollmentId);
    Page<AdvisoryEntity> findByEnrollmentId(String enrollmentId, Pageable pageable);
}
