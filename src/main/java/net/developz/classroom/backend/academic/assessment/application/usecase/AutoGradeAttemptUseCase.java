package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.AttemptAnswer;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class AutoGradeAttemptUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(String attemptId) {
        ExamAttempt attempt = examAttemptRepositoryPort.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException("ExamAttempt not found", AutoGradeAttemptUseCase.class,
                        ExamAttempt.class));

        // Placeholder for auto-grading logic.
        // In a real application, you would load the correct answers from the Catalog
        // Module
        // and compare them with the saved AttemptAnswers.

        double totalScore = 0.0;
        for (AttemptAnswer answer : attempt.getAnswers()) {
            if (answer.getScore() != null) {
                totalScore += answer.getScore().doubleValue();
            }
        }

        attempt.setScore(totalScore);
        return examAttemptRepositoryPort.save(attempt);
    }
}
