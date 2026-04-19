package net.developz.classroom.backend.catalog.resource.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.resource.application.port.ResourceRepositoryPort;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.repository.ResourceRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceRepositoryAdapter extends JpaRepositoryAdapter<Resource, String, ResourceRepository> implements ResourceRepositoryPort {

    public ResourceRepositoryAdapter(ResourceRepository repository) {
        super(repository);
    }

    @Override
    public List<Resource> findBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }
}
