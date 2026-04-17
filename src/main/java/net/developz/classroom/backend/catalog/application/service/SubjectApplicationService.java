package net.developz.classroom.backend.catalog.application.service;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.usecases.*;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectApplicationService {

    private final FindAllSubjectsUseCase findAll;
    private final FindSubjectByIdUseCase findById;
    private final CreateSubjectUseCase create;
    private final UpdateSubjectUseCase update;
    private final DeleteSubjectUseCase delete;

    public List<Subject> findAll() {
        return findAll.execute();
    }

    public Subject findById(String id) {
        return findById.execute(id);
    }

    public Subject create(Subject subject) {
        return create.execute(subject);
    }

    public Subject update(Subject subject) {
        return update.execute(subject);
    }

    public void delete(String id) {
        delete.execute(id);
    }
}
