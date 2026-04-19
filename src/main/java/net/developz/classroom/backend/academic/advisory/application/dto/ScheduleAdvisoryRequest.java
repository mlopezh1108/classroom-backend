package net.developz.classroom.backend.academic.advisory.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleAdvisoryRequest(
    String enrollmentId,
    LocalDate date,
    LocalTime time
) {}
