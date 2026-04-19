package net.developz.classroom.backend.academic.enrollment.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentDTO;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollStudentRequest;
import net.developz.classroom.backend.academic.enrollment.application.usecase.DropCourseUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.EnrollStudentUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.GetCourseRosterUseCase;
import net.developz.classroom.backend.academic.enrollment.application.usecase.GenerateEnrollmentReportUseCase;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentReportResponse;
import net.developz.classroom.backend.academic.enrollment.infrastructure.mapper.EnrollmentMapper;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Enrollment entity = mapper.toEntity(request);
        Enrollment saved = enrollStudentUseCase.execute(entity);
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dropCourse(@PathVariable String id) {
        dropCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getCourseRoster(@PathVariable String courseId) {
        List<Enrollment> enrollments = getCourseRosterUseCase.execute(courseId);
        List<EnrollmentDTO> dtoList = enrollments.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/report/period/{periodId}")
    public ResponseEntity<EnrollmentReportResponse> generateEnrollmentReport(@PathVariable String periodId) {
        EnrollmentReportResponse response = generateEnrollmentReportUseCase.execute(periodId);
        return ResponseEntity.ok(response);
    }
}
