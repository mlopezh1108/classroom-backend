package net.developz.classroom.backend.catalog.application.usecases;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class DeleteSubjectUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public void execute(String id) {
        if (!subjectRepositoryPort.existsById(id)) {
            throw new EntityNotFoundException(
                    "Subject with id " + id + " not found",
                    this.getClass(),
                    Subject.class
            );
        }
        subjectRepositoryPort.deleteById(id);
    }
}
