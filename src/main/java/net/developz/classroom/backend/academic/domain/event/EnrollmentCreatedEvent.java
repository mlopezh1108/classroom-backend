package net.developz.classroom.backend.academic.domain.event;

public record EnrollmentCreatedEvent(String enrollmentId, String studentId, String courseId) {
}
