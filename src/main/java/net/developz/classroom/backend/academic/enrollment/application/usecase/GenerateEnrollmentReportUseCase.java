package net.developz.classroom.backend.academic.enrollment.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.academic.enrollment.application.dto.EnrollmentReportResponse;
import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.shared.application.annotation.UseCase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GenerateEnrollmentReportUseCase {
    
    private final EnrollmentRepositoryPort enrollmentRepositoryPort;

    public EnrollmentReportResponse execute(String periodId) {
        // Naive implementation: In a real system, you'd likely use a custom repository query
        // joining the Course table to filter directly by periodId for performance.
        List<Enrollment> allEnrollments = enrollmentRepositoryPort.findAll();
        
        List<Enrollment> periodEnrollments = allEnrollments.stream()
                .filter(e -> e.getCourse() != null && periodId.equals(e.getCourse().getPeriodId()))
                .collect(Collectors.toList());
                
        Map<String, Long> countPerCourse = periodEnrollments.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCourse().getId(),
                        Collectors.counting()
                ));
                
        return new EnrollmentReportResponse(periodId, periodEnrollments.size(), countPerCourse);
    }
}
