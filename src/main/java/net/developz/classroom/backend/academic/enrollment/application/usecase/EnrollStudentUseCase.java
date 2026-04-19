package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentEventPublisherPort;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class EnrollStudentUseCase {
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;
    private final EnrollmentEventPublisherPort enrollmentEventPublisherPort;

    public Enrollment execute(Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentRepositoryPort.save(enrollment);
        enrollmentEventPublisherPort.publishEnrollmentCreated(savedEnrollment);
        return savedEnrollment;
    }
}
