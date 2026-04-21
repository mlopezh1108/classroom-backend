package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, String> {
    String BASE_QUERY = """
        SELECT e.enrollment_id as id, e.grade as grade, e.student_id as studentId, e.course_id as courseId,
               CONCAT(p.first_name, ' ', p.last_name) as studentName,
               c.course_code as courseCode, s.subject_name as subjectName
        FROM enrollment e
        LEFT JOIN student st ON e.student_id = st.student_id
        LEFT JOIN person p ON st.person_id = p.person_id
        LEFT JOIN course c ON e.course_id = c.course_id
        LEFT JOIN subject s ON c.subject_id = s.subject_id
        """;

    @org.springframework.data.jpa.repository.Query(value = BASE_QUERY + " WHERE e.enrollment_id = :id", nativeQuery = true)
    java.util.Optional<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findDetailedById(
            @org.springframework.data.repository.query.Param("id") String id);

    @org.springframework.data.jpa.repository.Query(value = BASE_QUERY, nativeQuery = true)
    List<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findAllDetailed();

    @org.springframework.data.jpa.repository.Query(value = BASE_QUERY, countQuery = "SELECT count(*) FROM enrollment", nativeQuery = true)
    Page<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findAllDetailed(Pageable pageable);

    @org.springframework.data.jpa.repository.Query(value = BASE_QUERY + " WHERE e.course_id = :courseId", nativeQuery = true)
    List<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findDetailedByCourseId(
            @org.springframework.data.repository.query.Param("courseId") String courseId);

    @org.springframework.data.jpa.repository.Query(
            value = BASE_QUERY + " WHERE e.course_id = :courseId", 
            countQuery = "SELECT count(*) FROM enrollment e WHERE e.course_id = :courseId", 
            nativeQuery = true)
    Page<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findDetailedByCourseId(
            @org.springframework.data.repository.query.Param("courseId") String courseId, Pageable pageable);

    @org.springframework.data.jpa.repository.Query(value = BASE_QUERY + " WHERE e.student_id = :studentId", nativeQuery = true)
    List<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findDetailedByStudentId(
            @org.springframework.data.repository.query.Param("studentId") String studentId);

    @org.springframework.data.jpa.repository.Query(
            value = BASE_QUERY + " WHERE e.student_id = :studentId", 
            countQuery = "SELECT count(*) FROM enrollment e WHERE e.student_id = :studentId", 
            nativeQuery = true)
    Page<net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection> findDetailedByStudentId(
            @org.springframework.data.repository.query.Param("studentId") String studentId, Pageable pageable);
}
