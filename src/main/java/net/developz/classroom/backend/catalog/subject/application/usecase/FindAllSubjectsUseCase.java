package net.developz.classroom.backend.catalog.subject.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllSubjectsUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public PaginatedResult<Subject> execute(PaginationCriteria criteria) {
        return subjectRepositoryPort.findAll(criteria);
    }

    public List<Subject> execute() {
        return subjectRepositoryPort.findAll();
    }
}

