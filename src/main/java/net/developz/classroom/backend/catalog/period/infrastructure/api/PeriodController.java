package net.developz.classroom.backend.catalog.period.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.dto.CreatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.dto.PeriodResponse;
import net.developz.classroom.backend.catalog.period.application.dto.UpdatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.usecase.*;
import net.developz.classroom.backend.catalog.period.infrastructure.mapper.PeriodMapper;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/periods")
@RequiredArgsConstructor
public class PeriodController {

    private final FindAllPeriodsUseCase findAllPeriodsUseCase;
    private final FindPeriodByIdUseCase findPeriodByIdUseCase;
    private final CreatePeriodUseCase createPeriodUseCase;
    private final UpdatePeriodUseCase updatePeriodUseCase;
    private final DeletePeriodUseCase deletePeriodUseCase;
    private final PeriodMapper periodMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('PERIOD_VIEW')")
    public ResponseEntity<List<PeriodResponse>> getAllPeriods() {
        List<PeriodResponse> periods = findAllPeriodsUseCase.execute().stream()
                .map(periodMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(periods);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERIOD_VIEW')")
    public ResponseEntity<PeriodResponse> getPeriodById(@PathVariable String id) {
        Period period = findPeriodByIdUseCase.execute(id);
        return ResponseEntity.ok(periodMapper.toDto(period));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PERIOD_CREATE')")
    public ResponseEntity<PeriodResponse> createPeriod(@RequestBody CreatePeriodRequest request) {
        Period period = periodMapper.toEntity(request);
        Period savedPeriod = createPeriodUseCase.execute(period);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodMapper.toDto(savedPeriod));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERIOD_UPDATE')")
    public ResponseEntity<PeriodResponse> updatePeriod(@PathVariable String id, @RequestBody UpdatePeriodRequest request) {
        Period existingPeriod = findPeriodByIdUseCase.execute(id);
        periodMapper.updateEntityFromRequest(request, existingPeriod);
        Period updatedPeriod = updatePeriodUseCase.execute(existingPeriod);
        return ResponseEntity.ok(periodMapper.toDto(updatedPeriod));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERIOD_DELETE')")
    public ResponseEntity<Void> deletePeriod(@PathVariable String id) {
        deletePeriodUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
