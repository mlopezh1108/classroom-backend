package net.developz.classroom.backend.academic.domain.events;

public record EnrollmentCreatedEvent(String enrollmentId, String studentId, String courseId) {
}
