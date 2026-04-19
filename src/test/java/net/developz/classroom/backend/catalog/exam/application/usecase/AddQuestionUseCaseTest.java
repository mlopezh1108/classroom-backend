package net.developz.classroom.backend.catalog.exam.application.usecase;

import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionOptionRequest;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateQuestionRequest;
import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Question;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionType;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.BooleanQuestion;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.MultipleChoiceQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddQuestionUseCaseTest {

    @Mock
    private ExamRepositoryPort examRepositoryPort;

    @InjectMocks
    private AddQuestionUseCase useCase;

    @Test
    void shouldAddBooleanQuestion() {
        String examId = "exam-1";
        CreateQuestionRequest request = new CreateQuestionRequest(examId, "Is Java fun?", 1, 10.0, QuestionType.BOOLEAN, true, null, null);
        Exam exam = new Exam();
        
        when(examRepositoryPort.findById(examId)).thenReturn(Optional.of(exam));
        when(examRepositoryPort.save(any(Exam.class))).thenReturn(exam);

        Exam result = useCase.execute(request);

        assertThat(result.getQuestions()).hasSize(1);
        Question added = result.getQuestions().get(0);
        assertThat(added).isInstanceOf(BooleanQuestion.class);
        assertThat(((BooleanQuestion) added).getCorrectAnswer()).isTrue();
    }

    @Test
    void shouldAddMultipleChoiceQuestion() {
        String examId = "exam-1";
        List<CreateQuestionOptionRequest> options = List.of(
                new CreateQuestionOptionRequest("Opt 1", true),
                new CreateQuestionOptionRequest("Opt 2", false)
        );
        CreateQuestionRequest request = new CreateQuestionRequest(examId, "Pick one", 1, 10.0, QuestionType.MULTIPLE_CHOICE, null, options, null);
        Exam exam = new Exam();

        when(examRepositoryPort.findById(examId)).thenReturn(Optional.of(exam));
        when(examRepositoryPort.save(any(Exam.class))).thenReturn(exam);

        Exam result = useCase.execute(request);

        assertThat(result.getQuestions()).hasSize(1);
        MultipleChoiceQuestion added = (MultipleChoiceQuestion) result.getQuestions().get(0);
        assertThat(added.getOptions()).hasSize(2);
    }
}
