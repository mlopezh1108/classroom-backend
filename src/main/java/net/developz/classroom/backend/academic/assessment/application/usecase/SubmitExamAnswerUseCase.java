package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.dto.SubmitAnswerRequest;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.domain.model.*;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class SubmitExamAnswerUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(String attemptId, SubmitAnswerRequest request) {
        ExamAttempt attempt = examAttemptRepositoryPort.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Attempt not found with id: " + attemptId, this.getClass(), ExamAttempt.class));

        if (attempt.getStatus() != AttemptStatus.STARTED) {
            throw new IllegalStateException("Cannot submit answers for an attempt that is not in STARTED status");
        }

        AttemptAnswer answer = switch (request.answerType()) {
            case BOOLEAN -> BooleanAttemptAnswer.builder()
                    .responseValue(Boolean.valueOf(request.responseValue()))
                    .build();
            case MULTIPLE_CHOICE -> MultipleChoiceAttemptAnswer.builder()
                    .selectedOptionId(request.responseValue())
                    .build();
            case OPEN -> OpenAttemptAnswer.builder()
                    .responseText(request.responseValue())
                    .build();
            case MATCHING -> MatchingAttemptAnswer.builder()
                    .build();
            default -> throw new IllegalArgumentException("Unknown answer type");
        };

        answer.setQuestionId(request.questionId());
        answer.setExamAttempt(attempt);

        attempt.getAnswers().add(answer);
        return examAttemptRepositoryPort.save(attempt);
    }
}
