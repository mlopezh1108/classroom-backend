package net.developz.classroom.backend.academic.enrollment.domain.event;

import java.io.Serializable;

public record EnrollmentCreatedEvent(
        String enrollmentId,
        String studentId,
        String courseId) implements Serializable {
}
