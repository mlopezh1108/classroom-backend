package net.developz.classroom.backend.academic.advisory.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.advisory.application.dto.AdvisoryDTO;
import net.developz.classroom.backend.academic.advisory.application.dto.RecordSessionRequest;
import net.developz.classroom.backend.academic.advisory.application.dto.ScheduleAdvisoryRequest;
import net.developz.classroom.backend.academic.advisory.application.usecase.ListAdvisoriesUseCase;
import net.developz.classroom.backend.academic.advisory.application.usecase.RecordAdvisorySessionUseCase;
import net.developz.classroom.backend.academic.advisory.application.usecase.ScheduleAdvisoryUseCase;
import net.developz.classroom.backend.academic.advisory.infrastructure.mapper.AdvisoryMapper;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Advisory entity = mapper.toEntity(request);
        Advisory saved = scheduleAdvisoryUseCase.execute(entity);
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
    public ResponseEntity<List<AdvisoryDTO>> listAdvisories(@PathVariable String enrollmentId) {
        List<Advisory> advisories = listAdvisoriesUseCase.execute(enrollmentId);
        List<AdvisoryDTO> dtoList = advisories.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
