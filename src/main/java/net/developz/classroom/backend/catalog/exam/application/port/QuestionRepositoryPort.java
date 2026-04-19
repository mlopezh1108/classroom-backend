package net.developz.classroom.backend.catalog.exam.application.port;

import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Question;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

public interface QuestionRepositoryPort extends RepositoryPort<Question, String> {
}
