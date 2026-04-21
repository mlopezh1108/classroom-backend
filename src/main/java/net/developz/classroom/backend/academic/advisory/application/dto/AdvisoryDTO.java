package net.developz.classroom.backend.academic.advisory.application.dto;

import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AdvisoryDTO(
    String id,
    String enrollmentId,
    AdvisoryStatus status,
    LocalDate date,
    LocalTime time,
    String notes
) {}
