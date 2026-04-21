package net.developz.classroom.backend.academic.enrollment.application.port;

import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;

public interface EnrollmentEventPublisherPort {
    void publishEnrollmentCreated(Enrollment enrollment);
}
