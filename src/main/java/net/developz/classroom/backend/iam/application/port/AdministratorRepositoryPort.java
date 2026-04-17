package net.developz.classroom.backend.iam.application.port;

import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Administrator;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

public interface AdministratorRepositoryPort extends RepositoryPort<Administrator, String> {
}
