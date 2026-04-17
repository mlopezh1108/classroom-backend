package net.developz.classroom.backend.catalog.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.application.service.SubjectApplicationService;
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

    private final SubjectApplicationService subjectService;
    private final SubjectMapper subjectMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('SUBJECT_VIEW')")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.findAll().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_VIEW')")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id) {
        Subject subject = subjectService.findById(id);
        return ResponseEntity.ok(subjectMapper.toDto(subject));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUBJECT_CREATE')")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody CreateSubjectRequest request) {
        Subject newSubject = subjectMapper.toEntity(request);
        Subject savedSubject = subjectService.create(newSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectMapper.toDto(savedSubject));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_UPDATE')")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable String id, @RequestBody UpdateSubjectRequest request) {
        Subject existingSubject = subjectService.findById(id);
        subjectMapper.updateEntityFromRequest(request, existingSubject);
        Subject updatedSubject = subjectService.update(existingSubject);
        return ResponseEntity.ok(subjectMapper.toDto(updatedSubject));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBJECT_DELETE')")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
