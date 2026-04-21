package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.mapper.AssessmentMapperImpl;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttemptEntity;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
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
@Import({ ExamAttemptRepositoryAdapter.class, AssessmentMapperImpl.class })
class ExamAttemptRepositoryAdapterTest {

    @Autowired
    private ExamAttemptRepositoryAdapter adapter;

    @Autowired
    private ExamAttemptRepository repository;

    @Test
    void shouldSaveAndFindById() {
        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setId("enroll-1");
        // No persist needed if it's just the ID for the attempt,
        // but it's better to persist if there are FK constraints

        ExamAttemptEntity attempt = new ExamAttemptEntity();
        attempt.setStatus(AttemptStatus.STARTED);
        attempt.setEnrollmentId(enrollment.getId());
        ExamAttemptEntity saved = repository.save(attempt);

        Optional<ExamAttempt> result = adapter.findById(saved.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getEnrollmentId()).isEqualTo("enroll-1");
    }
}
