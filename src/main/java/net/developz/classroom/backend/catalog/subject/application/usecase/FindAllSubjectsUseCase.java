package net.developz.classroom.backend.catalog.subject.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.subject.application.port.SubjectRepositoryPort;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllSubjectsUseCase {

    private final SubjectRepositoryPort subjectRepositoryPort;

    public Page<Subject> execute(Pageable pageable) {
        return subjectRepositoryPort.findAll(pageable);
    }

    public List<Subject> execute() {
        return subjectRepositoryPort.findAll();
    }
}

