package net.developz.classroom.backend.academic.infrastructure.persistence.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

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
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId("student-ulid-1");
        enrollment = entityManager.persist(enrollment);

        // Arrange Attempt
        ExamAttempt attempt = new ExamAttempt();
        attempt.setEnrollment(enrollment);
        attempt.setExamId("exam-ulid-1");

        // Arrange Polymorphic Answers
        BooleanAttemptAnswer booleanAnswer = new BooleanAttemptAnswer();
        booleanAnswer.setQuestionId("boolean-q-1");
        booleanAnswer.setExamAttempt(attempt);
        // Normally you'd set true/false if subclass has fields

        MultipleChoiceAttemptAnswer mcAnswer = new MultipleChoiceAttemptAnswer();
        mcAnswer.setQuestionId("mc-q-1");
        mcAnswer.setExamAttempt(attempt);

        attempt.getAnswers().add(booleanAnswer);
        attempt.getAnswers().add(mcAnswer);

        // Act
        ExamAttempt savedAttempt = entityManager.persistAndFlush(attempt);
        entityManager.clear(); // Clear context to force SQL query

        // Assert
        ExamAttempt retrieved = entityManager.find(ExamAttempt.class, savedAttempt.getId());
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getAnswers()).hasSize(2);
        
        // Assert Polymorphism Discriminator works!
        boolean hasBoolean = retrieved.getAnswers().stream().anyMatch(a -> a instanceof BooleanAttemptAnswer);
        boolean hasMc = retrieved.getAnswers().stream().anyMatch(a -> a instanceof MultipleChoiceAttemptAnswer);
        
        assertThat(hasBoolean).isTrue();
        assertThat(hasMc).isTrue();
    }
}
