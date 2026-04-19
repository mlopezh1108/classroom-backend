package net.developz.classroom.backend.catalog.exam.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class GetExamDetailsUseCase {
    private final ExamRepositoryPort examRepositoryPort;

    public Exam execute(String examId) {
        return examRepositoryPort.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found", GetExamDetailsUseCase.class, Exam.class));
    }
}
