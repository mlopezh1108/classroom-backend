package net.developz.classroom.backend.academic.course.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.repository.CourseRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseRepositoryAdapter
        extends JpaRepositoryAdapter<Course, String, CourseRepository>
        implements CourseRepositoryPort {

    public CourseRepositoryAdapter(CourseRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> findByTeacherId(String teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public List<Course> findByPeriodId(String periodId) {
        return repository.findByPeriodId(periodId);
    }
}
