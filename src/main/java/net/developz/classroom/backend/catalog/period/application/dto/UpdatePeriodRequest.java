package net.developz.classroom.backend.catalog.period.application.dto;

import java.time.LocalDate;

public record UpdatePeriodRequest(
    LocalDate startDate,
    LocalDate endDate
) {}
