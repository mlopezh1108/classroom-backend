package net.developz.classroom.backend.catalog.application;

import lombok.extern.slf4j.Slf4j;
import net.developz.classroom.backend.academic.domain.events.EnrollmentCreatedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CourseEnrollmentListener {

    /**
     * @ApplicationModuleListener is a Spring Modulith construct that is
     *                            equivalent to @Async
     *                            + @TransactionalEventListener.
     *                            It ensures this listener executes in a separate
     *                            thread AFTER the original
     *                            transaction successfully commits. If this listener
     *                            fails, the event remains
     *                            in the Event Publication Registry for retry.
     */
    @ApplicationModuleListener
    public void on(EnrollmentCreatedEvent event) {
        log.info("[CATALOG MODULE] Received EnrollmentCreatedEvent!");
        log.info("Student {} enrolled in Course {}", event.studentId(), event.courseId());

        // This is where the catalog module could update course quotas, etc.
        // e.g., courseService.decreaseAvailableSeats(event.courseId());
    }
}
