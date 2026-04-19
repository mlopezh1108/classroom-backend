package net.developz.classroom.backend.catalog.exam.application.usecase;

import net.developz.classroom.backend.catalog.exam.application.port.QuestionRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteQuestionUseCaseTest {

    @Mock
    private QuestionRepositoryPort questionRepositoryPort;

    @InjectMocks
    private DeleteQuestionUseCase useCase;

    @Test
    void shouldDeleteQuestion() {
        String questionId = "q-123";

        useCase.execute(questionId);

        verify(questionRepositoryPort).deleteById(questionId);
    }
}
