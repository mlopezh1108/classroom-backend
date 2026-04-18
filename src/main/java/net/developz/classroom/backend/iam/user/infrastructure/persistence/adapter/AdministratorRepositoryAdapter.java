package net.developz.classroom.backend.iam.user.infrastructure.persistence.adapter;

import net.developz.classroom.backend.iam.user.application.port.AdministratorRepositoryPort;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.Administrator;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.repository.AdministratorRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class AdministratorRepositoryAdapter extends JpaRepositoryAdapter<Administrator, String, AdministratorRepository>
        implements AdministratorRepositoryPort {

    public AdministratorRepositoryAdapter(AdministratorRepository repository) {
        super(repository);
    }
}



