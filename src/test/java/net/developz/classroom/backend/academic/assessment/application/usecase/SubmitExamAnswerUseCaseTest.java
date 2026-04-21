package net.developz.classroom.backend.academic.assessment.application.usecase;

import net.developz.classroom.backend.academic.assessment.application.dto.SubmitAnswerRequest;
import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.application.dto.AnswerType;
import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubmitExamAnswerUseCaseTest {

    @Mock
    private ExamAttemptRepositoryPort repositoryPort;

    @InjectMocks
    private SubmitExamAnswerUseCase useCase;

    @Test
    void shouldSubmitAnswer() {
        String attemptId = "att-1";
        ExamAttempt attempt = new ExamAttempt();
        attempt.setStatus(AttemptStatus.STARTED);
        
        SubmitAnswerRequest request = new SubmitAnswerRequest("q-1", AnswerType.BOOLEAN, "true");
        
        when(repositoryPort.findById(attemptId)).thenReturn(Optional.of(attempt));
        when(repositoryPort.save(any(ExamAttempt.class))).thenReturn(attempt);

        ExamAttempt result = useCase.execute(attemptId, request);

        assertThat(result.getAnswers()).hasSize(1);
        verify(repositoryPort).save(attempt);
    }

    @Test
    void shouldThrowExceptionWhenNotStarted() {
        String attemptId = "att-1";
        ExamAttempt attempt = new ExamAttempt();
        attempt.setStatus(AttemptStatus.SUBMITTED);
        
        SubmitAnswerRequest request = new SubmitAnswerRequest("q-1", AnswerType.BOOLEAN, "true");
        
        when(repositoryPort.findById(attemptId)).thenReturn(Optional.of(attempt));

        assertThatThrownBy(() -> useCase.execute(attemptId, request))
                .isInstanceOf(IllegalStateException.class);
    }
}
