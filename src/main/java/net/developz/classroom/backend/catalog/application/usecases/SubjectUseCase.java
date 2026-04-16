package net.developz.classroom.backend.catalog.application.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.SubjectRepository;

import java.util.List;

@RequiredArgsConstructor
public class SubjectUseCase {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(String id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
    }

    public Subject create(Subject subject) {
        if (subjectRepository.existsBySubjectCode(subject.getSubjectCode())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Subject with this code already exists");
        }
        return subjectRepository.save(subject);
    }

    public Subject update(Subject existingSubject) {
        return subjectRepository.save(existingSubject);
    }

    public void delete(String id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        subjectRepository.deleteById(id);
    }
}
