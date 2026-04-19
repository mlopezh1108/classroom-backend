package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.enums.AttemptStatus;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class FinishExamAttemptUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(String attemptId) {
        ExamAttempt attempt = examAttemptRepositoryPort.findById(attemptId)
                .orElseThrow(() -> new EntityNotFoundException("ExamAttempt not found", FinishExamAttemptUseCase.class, ExamAttempt.class));

        attempt.setEndTime(LocalDateTime.now());
        attempt.setStatus(AttemptStatus.SUBMITTED);
        
        return examAttemptRepositoryPort.save(attempt);
    }
}
