package net.developz.classroom.backend.academic.enrollment.application.dto;

import jakarta.validation.constraints.NotBlank;

public record EnrollStudentRequest(
    @NotBlank(message = "Student ID is required")
    String studentId,
    
    @NotBlank(message = "Course ID is required")
    String courseId
) {}
