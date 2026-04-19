package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    List<Enrollment> findByCourseId(String courseId);
    List<Enrollment> findByStudentId(String studentId);
}
