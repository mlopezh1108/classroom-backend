package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.BooleanAttemptAnswerEntity;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttemptEntity;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.MultipleChoiceAttemptAnswerEntity;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExamAttemptEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldPersistPolymorphicAttemptAnswers() {
        // Arrange Enrollment to satisfy constraint
        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setStudentId("student-ulid-1");
        enrollment = entityManager.persist(enrollment);

        // Arrange Attempt
        ExamAttemptEntity attempt = new ExamAttemptEntity();
        attempt.setEnrollmentId(enrollment.getId());
        attempt.setExamId("exam-ulid-1");

        // Arrange Polymorphic Answers
        BooleanAttemptAnswerEntity booleanAnswer = new BooleanAttemptAnswerEntity();
        booleanAnswer.setQuestionId("boolean-q-1");
        booleanAnswer.setExamAttempt(attempt);
        booleanAnswer.setResponseValue(true);

        MultipleChoiceAttemptAnswerEntity mcAnswer = new MultipleChoiceAttemptAnswerEntity();
        mcAnswer.setQuestionId("mc-q-1");
        mcAnswer.setExamAttempt(attempt);
        mcAnswer.setSelectedOptionId("opt-1");

        attempt.getAnswers().add(booleanAnswer);
        attempt.getAnswers().add(mcAnswer);

        // Act
        ExamAttemptEntity savedAttempt = entityManager.persistAndFlush(attempt);
        entityManager.clear(); // Clear context to force SQL query

        // Assert
        ExamAttemptEntity retrieved = entityManager.find(ExamAttemptEntity.class, savedAttempt.getId());
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getAnswers()).hasSize(2);

        // Assert Polymorphism Discriminator works!
        boolean hasBoolean = retrieved.getAnswers().stream().anyMatch(a -> a instanceof BooleanAttemptAnswerEntity);
        boolean hasMc = retrieved.getAnswers().stream().anyMatch(a -> a instanceof MultipleChoiceAttemptAnswerEntity);

        assertThat(hasBoolean).isTrue();
        assertThat(hasMc).isTrue();
    }
}
