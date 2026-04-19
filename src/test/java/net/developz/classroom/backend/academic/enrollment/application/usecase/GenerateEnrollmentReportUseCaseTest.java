package net.developz.classroom.backend.academic.enrollment.application.usecase;

import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentReportResponse;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenerateEnrollmentReportUseCaseTest {

    @Mock
    private EnrollmentRepositoryPort repositoryPort;

    @InjectMocks
    private GenerateEnrollmentReportUseCase useCase;

    @Test
    void shouldGenerateReport() {
        String periodId = "2026-1";
        
        Course course1 = new Course();
        course1.setId("c1");
        course1.setPeriodId(periodId);
        
        Course course2 = new Course();
        course2.setId("c2");
        course2.setPeriodId("other-period");
        
        Enrollment e1 = new Enrollment();
        e1.setCourse(course1);
        
        Enrollment e2 = new Enrollment();
        e2.setCourse(course1);
        
        Enrollment e3 = new Enrollment();
        e3.setCourse(course2);
        
        when(repositoryPort.findAll()).thenReturn(List.of(e1, e2, e3));

        EnrollmentReportResponse result = useCase.execute(periodId);

        assertThat(result.periodId()).isEqualTo(periodId);
        assertThat(result.totalEnrollments()).isEqualTo(2);
        assertThat(result.enrollmentsPerCourse()).containsEntry("c1", 2L);
        assertThat(result.enrollmentsPerCourse()).doesNotContainKey("c2");
    }
}
