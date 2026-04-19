package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.repository.EnrollmentRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnrollmentRepositoryAdapter extends JpaRepositoryAdapter<Enrollment, String, EnrollmentRepository> implements EnrollmentRepositoryPort {

    public EnrollmentRepositoryAdapter(EnrollmentRepository repository) {
        super(repository);
    }

    @Override
    public List<Enrollment> findByCourseId(String courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Enrollment> findByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }
}
