package net.developz.classroom.backend.catalog.exam.application.usecase;

import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetExamDetailsUseCaseTest {

    @Mock
    private ExamRepositoryPort examRepositoryPort;

    @InjectMocks
    private GetExamDetailsUseCase useCase;

    @Test
    void shouldGetExamDetails() {
        String examId = "exam-123";
        Exam exam = new Exam();
        when(examRepositoryPort.findById(examId)).thenReturn(Optional.of(exam));

        Exam result = useCase.execute(examId);

        assertThat(result).isEqualTo(exam);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        String examId = "unknown";
        when(examRepositoryPort.findById(examId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(examId))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
