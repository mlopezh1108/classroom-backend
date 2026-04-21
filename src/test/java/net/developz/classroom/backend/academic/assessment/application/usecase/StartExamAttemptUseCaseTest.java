package net.developz.classroom.backend.academic.assessment.application.usecase;

import net.developz.classroom.backend.academic.assessment.application.port.ExamAttemptRepositoryPort;
import net.developz.classroom.backend.academic.assessment.domain.model.ExamAttempt;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartExamAttemptUseCaseTest {

    @Mock
    private ExamAttemptRepositoryPort repositoryPort;

    @InjectMocks
    private StartExamAttemptUseCase useCase;

    @Test
    void shouldStartAttempt() {
        ExamAttempt attempt = new ExamAttempt();
        when(repositoryPort.save(attempt)).thenReturn(attempt);

        ExamAttempt result = useCase.execute(attempt);

        assertThat(result.getStatus()).isEqualTo(AttemptStatus.STARTED);
        assertThat(result.getStartTime()).isNotNull();
        verify(repositoryPort).save(attempt);
    }
}
