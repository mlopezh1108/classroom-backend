package net.developz.classroom.backend.academic.assessment.application.usecase;

import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity.enums.AttemptStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinishExamAttemptUseCaseTest {

    @Mock
    private ExamAttemptRepositoryPort repositoryPort;

    @InjectMocks
    private FinishExamAttemptUseCase useCase;

    @Test
    void shouldFinishAttempt() {
        String attemptId = "att-1";
        ExamAttempt attempt = new ExamAttempt();
        attempt.setStatus(AttemptStatus.STARTED);
        
        when(repositoryPort.findById(attemptId)).thenReturn(Optional.of(attempt));
        when(repositoryPort.save(any(ExamAttempt.class))).thenReturn(attempt);

        ExamAttempt result = useCase.execute(attemptId);

        assertThat(result.getStatus()).isEqualTo(AttemptStatus.SUBMITTED);
        assertThat(result.getEndTime()).isNotNull();
        verify(repositoryPort).save(attempt);
    }
}
