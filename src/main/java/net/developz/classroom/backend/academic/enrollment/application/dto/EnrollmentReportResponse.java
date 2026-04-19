package net.developz.classroom.backend.academic.enrollment.application.dto;

import java.util.Map;

public record EnrollmentReportResponse(
    String periodId,
    long totalEnrollments,
    Map<String, Long> enrollmentsPerCourse
) {}
