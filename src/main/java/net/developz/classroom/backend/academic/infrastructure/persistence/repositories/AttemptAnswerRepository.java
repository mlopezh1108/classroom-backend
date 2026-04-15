package net.developz.classroom.backend.academic.infrastructure.persistence.repositories;
 
import net.developz.classroom.backend.academic.infrastructure.persistence.entities.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, String> {
    List<AttemptAnswer> findByExamAttemptId(String attemptId);
}
