package net.developz.classroom.backend.academic.course.application.port;

import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CourseRepositoryPort extends RepositoryPort<Course, String> {
    List<Course> findByTeacherId(String teacherId);
    Page<Course> findByTeacherId(String teacherId, Pageable pageable);
    List<Course> findByPeriodId(String periodId);
    Page<Course> findByPeriodId(String periodId, Pageable pageable);
}
