package net.developz.classroom.backend.academic.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developz.classroom.backend.academic.domain.event.EnrollmentCreatedEvent;
import net.developz.classroom.backend.academic.infrastructure.persistence.entity.Enrollment;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void confirmEnrollment(Enrollment enrollment) {
        log.info("Processing enrollment for student: {} in course: {}",
                enrollment.getStudentId(),
                enrollment.getCourse().getId());

        // Here you would normally save the enrollment via a repository:
        // enrollmentRepository.save(enrollment);

        // 1. Create the Domain Event
        EnrollmentCreatedEvent event = new EnrollmentCreatedEvent(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourse().getId());

        // 2. Publish it. Because we are inside a @Transactional method,
        // Spring Modulith will ALSO intercept this event and save it
        // to the EVENT_PUBLICATION table for guaranteed delivery.
        log.info("Publishing EnrollmentCreatedEvent for enrollment {}", event.enrollmentId());
        eventPublisher.publishEvent(event);
    }
}
