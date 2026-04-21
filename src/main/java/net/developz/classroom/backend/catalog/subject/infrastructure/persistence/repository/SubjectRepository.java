package net.developz.classroom.backend.catalog.subject.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.SubjectEntity;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, String> {
    Optional<SubjectEntity> findBySubjectCode(String subjectCode);

    boolean existsBySubjectCode(String subjectCode);
}
