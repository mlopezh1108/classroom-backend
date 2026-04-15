package net.developz.classroom.backend.catalog.infrastructure.persistence.repositories;
 
import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
    List<Exam> findBySubjectId(String subjectId);
}
