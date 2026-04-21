package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.academic.enrollment.infrastructure.mapper.EnrollmentMapperImpl;
import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapperImpl;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseEntity;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.repository.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ EnrollmentRepositoryAdapter.class, EnrollmentMapperImpl.class, CourseMapperImpl.class })
class EnrollmentRepositoryAdapterTest {

    @Autowired
    private EnrollmentRepositoryAdapter adapter;

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindByCourseId() {
        CourseEntity course = new CourseEntity();
        course.setId("course-1");
        course.setCourseCode("C1");
        entityManager.persist(course);

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setCourse(course);
        enrollment.setStudentId("student-1");
        repository.save(enrollment);
        entityManager.flush();

        List<Enrollment> result = adapter.findByCourseId("course-1");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCourse().getId()).isEqualTo("course-1");
    }
}
