package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.enums.AdvisoryStatus;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.repository.AdvisoryRepository;
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
@Import(AdvisoryRepositoryAdapter.class)
class AdvisoryRepositoryAdapterTest {

    @Autowired
    private AdvisoryRepositoryAdapter adapter;

    @Autowired
    private AdvisoryRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindByStudentId() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId("enroll-1");
        entityManager.persist(enrollment);
        
        Advisory advisory = new Advisory();
        advisory.setEnrollmentId(enrollment.getId());
        advisory.setStatus(AdvisoryStatus.SCHEDULED);
        repository.save(advisory);
        entityManager.flush();

        List<Advisory> result = adapter.findByEnrollmentId("enroll-1");

        assertThat(result).isNotNull();
    }
}
