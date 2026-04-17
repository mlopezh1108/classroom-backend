package net.developz.classroom.backend.catalog.application.usecases;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.application.exception.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
public class FindSubjectByIdUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public Subject execute(String id) {
        return subjectRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Subject with id " + id + " not found",
                        this.getClass(),
                        Subject.class
                ));
    }
}
