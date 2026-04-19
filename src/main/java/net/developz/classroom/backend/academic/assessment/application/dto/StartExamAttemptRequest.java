package net.developz.classroom.backend.academic.assessment.application.dto;

public record StartExamAttemptRequest(
    String enrollmentId,
    String examId
) {}
