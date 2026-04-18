package net.developz.classroom.backend.academic.enrollment.infrastructure.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developz.classroom.backend.academic.enrollment.domain.event.EnrollmentCreatedEvent;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void confirmEnrollment(Enrollment enrollment) {
        log.info("Processing enrollment for student: {} in course: {}",
                enrollment.getStudentId(),
                enrollment.getCourse().getId());

        EnrollmentCreatedEvent event = new EnrollmentCreatedEvent(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourse().getId());

        log.info("Publishing EnrollmentCreatedEvent for enrollment {}", event.enrollmentId());
        eventPublisher.publishEvent(event);
    }
}
