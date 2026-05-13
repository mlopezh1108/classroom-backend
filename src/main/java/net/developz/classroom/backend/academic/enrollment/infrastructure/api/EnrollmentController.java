package net.developz.classroom.backend.academic.enrollment.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.dto.*;
import net.developz.classroom.backend.academic.enrollment.application.usecase.*;
import net.developz.classroom.backend.academic.enrollment.infrastructure.mapper.EnrollmentMapper;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.shared.application.dto.PageResponse;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollStudentUseCase enrollStudentUseCase;
    private final DropCourseUseCase dropCourseUseCase;
    private final GetCourseRosterUseCase getCourseRosterUseCase;
    private final GenerateEnrollmentReportUseCase generateEnrollmentReportUseCase;
    private final EnrollmentMapper mapper;

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enrollStudent(@Valid @RequestBody EnrollStudentRequest request) {
        Enrollment model = mapper.toModel(request);
        Enrollment saved = enrollStudentUseCase.execute(model);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dropCourse(@PathVariable String id) {
        dropCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roster/{courseId}")
    @PreAuthorize("hasAuthority('ENROLL_VIEW')")
    public ResponseEntity<PageResponse<EnrollmentDTO>> getCourseRoster(
            @PathVariable String courseId,
            Pageable pageable) {
        PaginationCriteria criteria = PaginationCriteria.of(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(PageResponse.from(
                getCourseRosterUseCase.execute(courseId, criteria).map(mapper::toDto)
        ));
    }

    @GetMapping("/report/period/{periodId}")
    public ResponseEntity<EnrollmentReportResponse> generateEnrollmentReport(@PathVariable String periodId) {
        EnrollmentReportResponse response = generateEnrollmentReportUseCase.execute(periodId);
        return ResponseEntity.ok(response);
    }
}
