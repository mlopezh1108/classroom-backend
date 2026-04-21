package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.exam.domain.model.Exam;
import net.developz.classroom.backend.catalog.exam.infrastructure.mapper.ExamMapperImpl;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.ExamEntity;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.SubjectEntity;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository.ExamRepository;
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
@Import({ ExamRepositoryAdapter.class, ExamMapperImpl.class })
class ExamRepositoryAdapterTest {

    @Autowired
    private ExamRepositoryAdapter adapter;

    @Autowired
    private ExamRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindBySubjectId() {
        SubjectEntity subject = new SubjectEntity();
        subject.setId("sub-1");
        subject.setSubjectName("Test Subject");
        subject.setSubjectCode("SUB-1");
        entityManager.persist(subject);

        ExamEntity exam = new ExamEntity();
        exam.setTitle("Test Exam");
        exam.setSubjectId(subject.getId());
        repository.save(exam);
        entityManager.flush();

        List<Exam> result = adapter.findBySubjectId("sub-1");
        assertThat(result).hasSize(1);
    }
}
