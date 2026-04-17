package net.developz.classroom.backend.catalog.application.usecases;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class UpdateSubjectUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public Subject execute(Subject subject) {
        return subjectRepositoryPort.save(subject);
    }
}
