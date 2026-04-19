package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.enums.AttemptStatus;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.repository.ExamAttemptRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ExamAttemptRepositoryAdapter.class)
class ExamAttemptRepositoryAdapterTest {

    @Autowired
    private ExamAttemptRepositoryAdapter adapter;

    @Autowired
    private ExamAttemptRepository repository;

    @Test
    void shouldSaveAndFindById() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId("enroll-1");
        
        ExamAttempt attempt = new ExamAttempt();
        attempt.setStatus(AttemptStatus.STARTED);
        attempt.setEnrollmentId(enrollment.getId());
        ExamAttempt saved = repository.save(attempt);

        Optional<ExamAttempt> result = adapter.findById(saved.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getEnrollmentId()).isEqualTo("enroll-1");
    }
}
