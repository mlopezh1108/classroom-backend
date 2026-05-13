package net.developz.classroom.backend.academic.course.application.port;

import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;

public interface CourseRepositoryPort extends RepositoryPort<Course, String> {
    List<Course> findByTeacherId(String teacherId);
    PaginatedResult<Course> findByTeacherId(String teacherId, PaginationCriteria criteria);
    List<Course> findByPeriodId(String periodId);
    PaginatedResult<Course> findByPeriodId(String periodId, PaginationCriteria criteria);
}
