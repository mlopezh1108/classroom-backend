package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.advisory.application.port.AdvisoryRepositoryPort;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.repository.AdvisoryRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdvisoryRepositoryAdapter extends JpaRepositoryAdapter<Advisory, String, AdvisoryRepository> implements AdvisoryRepositoryPort {

    public AdvisoryRepositoryAdapter(AdvisoryRepository repository) {
        super(repository);
    }

    @Override
    public List<Advisory> findByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId);
    }
}
