package net.developz.classroom.backend.academic.enrollment.application.port;

import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;

public interface EnrollmentEventPublisherPort {
    void publishEnrollmentCreated(Enrollment enrollment);
}
