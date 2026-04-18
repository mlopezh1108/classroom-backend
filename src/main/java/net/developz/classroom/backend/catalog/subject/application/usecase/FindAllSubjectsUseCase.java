package net.developz.classroom.backend.catalog.subject.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllSubjectsUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public List<Subject> execute() {
        return subjectRepositoryPort.findAll();
    }
}


