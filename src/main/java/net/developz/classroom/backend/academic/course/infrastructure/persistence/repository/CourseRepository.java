package net.developz.classroom.backend.academic.course.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByTeacherId(String teacherId);
    List<Course> findByPeriodId(String periodId);
}
