package net.developz.classroom.backend.catalog.infrastructure.persistence.repositories;
 
import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByExamIdOrderByOrderIndexAsc(String examId);
}
