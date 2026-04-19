package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.dto.SubmitAnswerRequest;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.*;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.enums.AttemptStatus;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class SubmitExamAnswerUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(String attemptId, SubmitAnswerRequest request) {
        ExamAttempt attempt = examAttemptRepositoryPort.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException("ExamAttempt not found", SubmitExamAnswerUseCase.class, ExamAttempt.class));

        if (attempt.getStatus() != AttemptStatus.STARTED) {
            throw new IllegalStateException("Cannot submit answers for an attempt that is not in STARTED status");
        }

        AttemptAnswer answer;
        switch (request.answerType()) {
            case BOOLEAN -> {
                BooleanAttemptAnswer boolAnswer = new BooleanAttemptAnswer();
                boolAnswer.setResponseValue(Boolean.valueOf(request.responseValue()));
                answer = boolAnswer;
            }
            case MULTIPLE_CHOICE -> {
                MultipleChoiceAttemptAnswer mcAnswer = new MultipleChoiceAttemptAnswer();
                mcAnswer.setSelectedOptionId(request.responseValue());
                answer = mcAnswer;
            }
            case OPEN -> {
                OpenAttemptAnswer openAnswer = new OpenAttemptAnswer();
                openAnswer.setResponseText(request.responseValue());
                answer = openAnswer;
            }
            case MATCHING -> {
                // Simplified matching: mapping a single string. Advanced matching would need a different DTO.
                MatchingAttemptAnswer matchAnswer = new MatchingAttemptAnswer();
                answer = matchAnswer;
            }
            default -> throw new IllegalArgumentException("Unknown answer type");
        }

        answer.setQuestionId(request.questionId());
        answer.setExamAttempt(attempt);

        attempt.getAnswers().add(answer);
        return examAttemptRepositoryPort.save(attempt);
    }
}
