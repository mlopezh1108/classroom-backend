package net.developz.classroom.backend.academic.assessment.application.dto;

import java.math.BigDecimal;

public record GradeAssessmentRequest(
    String answerId,
    BigDecimal score
) {}
