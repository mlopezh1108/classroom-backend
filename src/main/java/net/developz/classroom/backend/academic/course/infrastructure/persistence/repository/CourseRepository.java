package net.developz.classroom.backend.academic.course.infrastructure.persistence.repository;

import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseEntity;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    String BASE_QUERY = """
            SELECT c.course_id as id, c.course_code as courseCode, c.status as status,
                   c.teacher_id as teacherId, c.group_id as groupId,
                   c.subject_id as subjectId, c.period_id as periodId,
                   CONCAT(p.first_name, ' ', p.last_name) as teacherName,
                   g.group_code as groupCode, s.subject_name as subjectName,
                   pr.period_code as periodCode
            FROM course c
            LEFT JOIN teacher t ON c.teacher_id = t.teacher_id
            LEFT JOIN person p ON t.person_id = p.person_id
            LEFT JOIN group_entity g ON c.group_id = g.group_id
            LEFT JOIN subject s ON c.subject_id = s.subject_id
            LEFT JOIN period pr ON c.period_id = pr.period_id
            """;

    @Query(value = BASE_QUERY + " WHERE c.course_id = :id", nativeQuery = true)
    java.util.Optional<CourseDetailsProjection> findDetailedById(
            @Param("id") String id);

    @Query(value = BASE_QUERY, nativeQuery = true)
    List<CourseDetailsProjection> findAllDetailed();

    @Query(value = BASE_QUERY, countQuery = "SELECT count(*) FROM course", nativeQuery = true)
    Page<CourseDetailsProjection> findAllDetailed(Pageable pageable);

    @Query(value = BASE_QUERY + " WHERE c.teacher_id = :teacherId", nativeQuery = true)
    List<CourseDetailsProjection> findDetailedByTeacherId(
            @Param("teacherId") String teacherId);

    @Query(value = BASE_QUERY
            + " WHERE c.teacher_id = :teacherId", countQuery = "SELECT count(*) FROM course c WHERE c.teacher_id = :teacherId", nativeQuery = true)
    Page<CourseDetailsProjection> findDetailedByTeacherId(
            @Param("teacherId") String teacherId, Pageable pageable);

    @Query(value = BASE_QUERY + " WHERE c.period_id = :periodId", nativeQuery = true)
    List<CourseDetailsProjection> findDetailedByPeriodId(
            @Param("periodId") String periodId);

    @Query(value = BASE_QUERY
            + " WHERE c.period_id = :periodId", countQuery = "SELECT count(*) FROM course c WHERE c.period_id = :periodId", nativeQuery = true)
    Page<CourseDetailsProjection> findDetailedByPeriodId(
            @Param("periodId") String periodId, Pageable pageable);
}
