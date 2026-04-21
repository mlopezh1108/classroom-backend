package net.developz.classroom.backend.academic.enrollment.application.dto;

import java.math.BigDecimal;

public record EnrollmentDTO(
    String id,
    String studentName,
    String courseCode,
    String subjectName,
    BigDecimal grade
) {}
