package net.developz.classroom.backend.academic.advisory.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.dto.*;
import net.developz.classroom.backend.academic.advisory.application.usecase.*;
import net.developz.classroom.backend.academic.advisory.infrastructure.mapper.AdvisoryMapper;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.shared.application.dto.PageResponse;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/advisories")
@RequiredArgsConstructor
public class AdvisoryController {

    private final ScheduleAdvisoryUseCase scheduleAdvisoryUseCase;
    private final RecordAdvisorySessionUseCase recordAdvisorySessionUseCase;
    private final ListAdvisoriesUseCase listAdvisoriesUseCase;
    private final AdvisoryMapper mapper;

    @PostMapping("/schedule")
    public ResponseEntity<AdvisoryDTO> scheduleAdvisory(@RequestBody ScheduleAdvisoryRequest request) {
        Advisory model = mapper.toModel(request);
        Advisory saved = scheduleAdvisoryUseCase.execute(model);
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PostMapping("/{advisoryId}/record")
    public ResponseEntity<AdvisoryDTO> recordSession(
            @PathVariable String advisoryId,
            @RequestBody RecordSessionRequest request) {
        Advisory attempt = recordAdvisorySessionUseCase.execute(advisoryId, request);
        return ResponseEntity.ok(mapper.toDTO(attempt));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    @PreAuthorize("hasAuthority('ADVISORY_VIEW')")
    public ResponseEntity<PageResponse<AdvisoryDTO>> listAdvisories(
            @PathVariable String enrollmentId,
            Pageable pageable) {
        PaginationCriteria criteria = PaginationCriteria.of(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(PageResponse.from(
                listAdvisoriesUseCase.execute(enrollmentId, criteria).map(mapper::toDTO)
        ));
    }
}
