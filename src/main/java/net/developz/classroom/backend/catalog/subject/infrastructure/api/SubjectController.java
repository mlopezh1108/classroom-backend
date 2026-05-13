package net.developz.classroom.backend.catalog.subject.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.subject.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.subject.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.subject.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.subject.application.usecase.CreateSubjectUseCase;
import net.developz.classroom.backend.catalog.subject.application.usecase.DeleteSubjectUseCase;
import net.developz.classroom.backend.catalog.subject.application.usecase.FindAllSubjectsUseCase;
import net.developz.classroom.backend.catalog.subject.application.usecase.FindSubjectByIdUseCase;
import net.developz.classroom.backend.catalog.subject.application.usecase.UpdateSubjectUseCase;
import net.developz.classroom.backend.catalog.subject.infrastructure.mapper.SubjectMapper;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.shared.application.dto.PageResponse;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageResponse<SubjectDTO>> getAllSubjects(Pageable pageable) {
        PaginationCriteria criteria = PaginationCriteria.of(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(PageResponse.from(
                findAllSubjectsUseCase.execute(criteria).map(subjectMapper::toDto)));
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
        Subject newSubject = subjectMapper.toModel(request);
        Subject savedSubject = createSubjectUseCase.execute(newSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectMapper.toDto(savedSubject));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_UPDATE')")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String id,
            @RequestBody UpdateSubjectRequest request) {
        Subject existingSubject = findSubjectByIdUseCase.execute(id);
        subjectMapper.updateModelFromRequest(request, existingSubject);
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
