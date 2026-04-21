package net.developz.classroom.backend.catalog.resource.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.ResourceEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {
    List<ResourceEntity> findBySubjectId(String subjectId);
    Page<ResourceEntity> findBySubjectId(String subjectId, Pageable pageable);
}


