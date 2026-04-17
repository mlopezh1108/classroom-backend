package net.developz.classroom.backend.catalog.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.application.usecases.CreateSubjectUseCase;
import net.developz.classroom.backend.catalog.application.usecases.DeleteSubjectUseCase;
import net.developz.classroom.backend.catalog.application.usecases.FindAllSubjectsUseCase;
import net.developz.classroom.backend.catalog.application.usecases.FindSubjectByIdUseCase;
import net.developz.classroom.backend.catalog.application.usecases.UpdateSubjectUseCase;
import net.developz.classroom.backend.catalog.infrastructure.mapper.SubjectMapper;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final FindAllSubjectsUseCase findAllSubjectsUseCase;
    private final FindSubjectByIdUseCase findSubjectByIdUseCase;
    private final CreateSubjectUseCase createSubjectUseCase;
    private final UpdateSubjectUseCase updateSubjectUseCase;
    private final DeleteSubjectUseCase deleteSubjectUseCase;
    private final SubjectMapper subjectMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('SUBJECT_VIEW')")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = findAllSubjectsUseCase.execute().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_VIEW')")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id) {
        Subject subject = findSubjectByIdUseCase.execute(id);
        return ResponseEntity.ok(subjectMapper.toDto(subject));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUBJECT_CREATE')")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody CreateSubjectRequest request) {
        Subject newSubject = subjectMapper.toEntity(request);
        Subject savedSubject = createSubjectUseCase.execute(newSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectMapper.toDto(savedSubject));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_UPDATE')")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String id, @RequestBody UpdateSubjectRequest request) {
        Subject existingSubject = findSubjectByIdUseCase.execute(id);
        subjectMapper.updateEntityFromRequest(request, existingSubject);
        Subject updatedSubject = updateSubjectUseCase.execute(existingSubject);
        return ResponseEntity.ok(subjectMapper.toDto(updatedSubject));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_DELETE')")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        deleteSubjectUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
