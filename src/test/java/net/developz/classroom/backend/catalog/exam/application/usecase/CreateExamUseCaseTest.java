package net.developz.classroom.backend.catalog.exam.application.usecase;

import net.developz.classroom.backend.catalog.exam.application.dto.CreateExamRequest;
import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;
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
class CreateExamUseCaseTest {

    @Mock
    private ExamRepositoryPort examRepositoryPort;

    @Mock
    private SubjectRepositoryPort subjectRepositoryPort;

    @InjectMocks
    private CreateExamUseCase useCase;

    @Test
    void shouldCreateExam() {
        String subjectId = "sub-1";
        CreateExamRequest request = new CreateExamRequest("Exam 1", "Review", subjectId);
        Subject subject = new Subject();
        
        when(subjectRepositoryPort.existsById(subjectId)).thenReturn(true);
        when(examRepositoryPort.save(any(Exam.class))).thenReturn(new Exam());

        Exam result = useCase.execute(request);

        assertThat(result).isNotNull();
        verify(examRepositoryPort).save(any(Exam.class));
    }

    @Test
    void shouldThrowExceptionWhenSubjectNotFound() {
        String subjectId = "unknown";
        CreateExamRequest request = new CreateExamRequest("Exam 1", "Review", subjectId);
        
        when(subjectRepositoryPort.existsById(subjectId)).thenReturn(false);

        assertThatThrownBy(() -> useCase.execute(request))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
