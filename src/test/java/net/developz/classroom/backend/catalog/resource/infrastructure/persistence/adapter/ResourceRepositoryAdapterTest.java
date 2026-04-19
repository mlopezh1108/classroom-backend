package net.developz.classroom.backend.catalog.resource.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.repository.ResourceRepository;
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
@Import(ResourceRepositoryAdapter.class)
class ResourceRepositoryAdapterTest {

    @Autowired
    private ResourceRepositoryAdapter adapter;

    @Autowired
    private ResourceRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveAndFindBySubjectId() {
        Subject subject = new Subject();
        subject.setId("sub-1");
        entityManager.persist(subject);

        Resource resource = new Resource();
        resource.setTitle("Test Resource");
        resource.setResourceType(ResourceType.PDF);
        resource.setContentUrl("test-url");
        resource.setSubjectId(subject.getId());
        repository.save(resource);
        entityManager.flush();

        List<Resource> result = adapter.findBySubjectId("sub-1");
        assertThat(result).hasSize(1);
    }
}
