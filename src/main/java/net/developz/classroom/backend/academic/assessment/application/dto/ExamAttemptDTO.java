package net.developz.classroom.backend.academic.assessment.application.dto;

import java.time.LocalDateTime;
import net.developz.classroom.backend.academic.assessment.domain.model.enums.AttemptStatus;

public record ExamAttemptDTO(
    String id,
    String enrollmentId,
    String examId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    Double score,
    AttemptStatus status
) {}
