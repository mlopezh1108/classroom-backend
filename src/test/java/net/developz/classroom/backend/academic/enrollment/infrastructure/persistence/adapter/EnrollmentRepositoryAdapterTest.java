package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.Course;
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
@Import(EnrollmentRepositoryAdapter.class)
class EnrollmentRepositoryAdapterTest {

    @Autowired
    private EnrollmentRepositoryAdapter adapter;

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindByCourseId() {
        Course course = new Course();
        course.setId("course-1");
        entityManager.persist(course);
        
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudentId("student-1");
        repository.save(enrollment);
        entityManager.flush();

        List<Enrollment> result = adapter.findByCourseId("course-1");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCourse().getId()).isEqualTo("course-1");
    }
}
