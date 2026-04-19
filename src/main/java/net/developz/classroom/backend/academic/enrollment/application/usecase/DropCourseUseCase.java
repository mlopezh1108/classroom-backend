package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

@UseCase
@RequiredArgsConstructor
public class DropCourseUseCase {
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;

    public void execute(String enrollmentId) {
        enrollmentRepositoryPort.deleteById(enrollmentId);
    }
}
