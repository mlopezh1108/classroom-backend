package net.developz.classroom.backend.academic.infrastructure.publisher;

import net.developz.classroom.backend.academic.enrollment.domain.event.EnrollmentCreatedEvent;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.academic.enrollment.infrastructure.publisher.EnrollmentPublisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EnrollmentPublisherTest {

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private EnrollmentPublisher enrollmentService;

    private Enrollment enrollment;
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setId("course-ulid-123");

        enrollment = new Enrollment();
        enrollment.setId("enrollment-ulid-456");
        enrollment.setStudentId("student-ulid-789");
        enrollment.setCourse(course);
    }

    @Test
    void publishEnrollmentCreated_ShouldPublishEvent() {
        // Act
        enrollmentService.publishEnrollmentCreated(enrollment);

        // Assert
        ArgumentCaptor<EnrollmentCreatedEvent> eventCaptor = ArgumentCaptor.forClass(EnrollmentCreatedEvent.class);
        verify(eventPublisher).publishEvent(eventCaptor.capture());

        EnrollmentCreatedEvent emittedEvent = eventCaptor.getValue();
        assertEquals("enrollment-ulid-456", emittedEvent.enrollmentId());
        assertEquals("student-ulid-789", emittedEvent.studentId());
        assertEquals("course-ulid-123", emittedEvent.courseId());
    }
}
