package net.developz.classroom.backend.catalog.exam.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.exam.application.port.QuestionRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteQuestionUseCase {
    private final QuestionRepositoryPort questionRepositoryPort;

    public void execute(String questionId) {
        questionRepositoryPort.deleteById(questionId);
    }
}
