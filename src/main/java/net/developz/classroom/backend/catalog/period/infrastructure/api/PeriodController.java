package net.developz.classroom.backend.catalog.period.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.dto.CreatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.dto.PeriodResponse;
import net.developz.classroom.backend.catalog.period.application.dto.UpdatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.usecase.*;
import net.developz.classroom.backend.catalog.period.infrastructure.mapper.PeriodMapper;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.dto.PageResponse;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageResponse<PeriodResponse>> getAllPeriods(Pageable pageable) {
        PaginationCriteria criteria = PaginationCriteria.of(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(PageResponse.from(
                findAllPeriodsUseCase.execute(criteria).map(periodMapper::toDto)));
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
        Period period = periodMapper.toModel(request);
        Period savedPeriod = createPeriodUseCase.execute(period);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodMapper.toDto(savedPeriod));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PERIOD_UPDATE')")
    public ResponseEntity<PeriodResponse> updatePeriod(@PathVariable String id,
            @RequestBody UpdatePeriodRequest request) {
        Period existingPeriod = findPeriodByIdUseCase.execute(id);
        periodMapper.updateModelFromRequest(request, existingPeriod);
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
