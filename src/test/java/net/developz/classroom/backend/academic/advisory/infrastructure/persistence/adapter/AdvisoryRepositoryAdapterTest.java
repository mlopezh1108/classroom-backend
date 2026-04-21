package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.mapper.AdvisoryMapperImpl;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.AdvisoryEntity;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;
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
@Import({ AdvisoryRepositoryAdapter.class, AdvisoryMapperImpl.class })
class AdvisoryRepositoryAdapterTest {

    @Autowired
    private AdvisoryRepositoryAdapter adapter;

    @Autowired
    private AdvisoryRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindByStudentId() {
        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setId("enroll-1");
        entityManager.persist(enrollment);

        AdvisoryEntity advisory = new AdvisoryEntity();
        advisory.setEnrollmentId(enrollment.getId());
        advisory.setStatus(AdvisoryStatus.SCHEDULED);
        repository.save(advisory);
        entityManager.flush();

        List<Advisory> result = adapter.findByEnrollmentId("enroll-1");

        assertThat(result).isNotNull();
    }
}
