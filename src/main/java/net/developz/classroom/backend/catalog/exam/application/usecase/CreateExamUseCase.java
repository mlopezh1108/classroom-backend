package net.developz.classroom.backend.catalog.exam.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.exam.application.dto.CreateExamRequest;
import net.developz.classroom.backend.catalog.exam.application.port.ExamRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Exam;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class CreateExamUseCase {
    private final ExamRepositoryPort examRepositoryPort;
    private final SubjectRepositoryPort subjectRepositoryPort;

    public Exam execute(CreateExamRequest request) {
        if (!subjectRepositoryPort.existsById(request.subjectId())) {
            throw new EntityNotFoundException("Subject not found: " + request.subjectId(), CreateExamUseCase.class, null);
        }

        Exam exam = new Exam();
        exam.setTitle(request.title());
        exam.setDescription(request.description());
        exam.setSubjectId(request.subjectId());
        
        return examRepositoryPort.save(exam);
    }
}
