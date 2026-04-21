package net.developz.classroom.backend.academic.course.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapperImpl;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseEntity;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ CourseRepositoryAdapter.class, CourseMapperImpl.class })
class CourseRepositoryAdapterTest {

    @Autowired
    private CourseRepositoryAdapter adapter;

    @Autowired
    private CourseRepository repository;

    @Test
    void shouldSaveAndFindByTeacherId() {
        CourseEntity course = new CourseEntity();
        course.setCourseCode("MATH101");
        course.setTeacherId("teacher-1");
        course.setSubjectId("sub-1");
        course.setPeriodId("per-1");
        repository.save(course);

        List<Course> result = adapter.findByTeacherId("teacher-1");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTeacherId()).isEqualTo("teacher-1");
    }
}
