package net.developz.classroom.backend.catalog.subject.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityAlreadyExistsException;

@UseCase
@RequiredArgsConstructor
public class CreateSubjectUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public Subject execute(Subject subject) {
        if (subjectRepositoryPort.existsBySubjectCode(subject.getSubjectCode())) {
            throw new EntityAlreadyExistsException(
                    "Subject with code " + subject.getSubjectCode() + " already exists",
                    this.getClass(),
                    Subject.class);
        }
        return subjectRepositoryPort.save(subject);
    }
}


