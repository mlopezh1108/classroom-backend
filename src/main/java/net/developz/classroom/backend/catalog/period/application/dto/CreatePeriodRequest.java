package net.developz.classroom.backend.catalog.period.application.dto;

import java.time.LocalDate;

public record CreatePeriodRequest(
    String periodCode,
    LocalDate startDate,
    LocalDate endDate
) {}
