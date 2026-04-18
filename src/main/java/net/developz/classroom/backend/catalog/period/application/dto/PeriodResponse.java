package net.developz.classroom.backend.catalog.period.application.dto;

import java.time.LocalDate;

public record PeriodResponse(
    String id,
    String periodCode,
    LocalDate startDate,
    LocalDate endDate
) {}
