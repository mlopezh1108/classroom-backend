package net.developz.classroom.backend.catalog.infrastructure.persistence.repositories;
 
import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {
    List<Resource> findBySubjectId(String subjectId);
}
