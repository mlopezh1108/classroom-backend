package net.developz.classroom.backend.academic.assessment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class StartExamAttemptUseCase {
    private final ExamAttemptRepositoryPort examAttemptRepositoryPort;

    public ExamAttempt execute(ExamAttempt attempt) {
        attempt.setStartTime(LocalDateTime.now());
        attempt.setStatus(AttemptStatus.STARTED);
        return examAttemptRepositoryPort.save(attempt);
    }
}
