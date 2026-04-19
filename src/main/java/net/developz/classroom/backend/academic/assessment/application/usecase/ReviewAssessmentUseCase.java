package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.dto.GradeAssessmentRequest;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.AttemptAnswer;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReviewAssessmentUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(String attemptId, List<GradeAssessmentRequest> gradingRequests) {
        ExamAttempt attempt = examAttemptRepositoryPort.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException("ExamAttempt not found", ReviewAssessmentUseCase.class, ExamAttempt.class));

        for (GradeAssessmentRequest request : gradingRequests) {
            for (AttemptAnswer answer : attempt.getAnswers()) {
                if (answer.getId().equals(request.answerId())) {
                    answer.setScore(request.score());
                    break;
                }
            }
        }

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
