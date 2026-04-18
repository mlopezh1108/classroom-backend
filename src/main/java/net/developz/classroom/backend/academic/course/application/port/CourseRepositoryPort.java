package net.developz.classroom.backend.academic.course.application.port;

import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

import java.util.List;

public interface CourseRepositoryPort extends RepositoryPort<Course, String> {
    List<Course> findByTeacherId(String teacherId);
    List<Course> findByPeriodId(String periodId);
}
