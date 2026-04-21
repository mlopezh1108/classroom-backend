package net.developz.classroom.backend.catalog.exam.application.port;

import net.developz.classroom.backend.catalog.exam.domain.model.Question;
import net.developz.classroom.backend.shared.application.port.RepositoryPort;

public interface QuestionRepositoryPort extends RepositoryPort<Question, String> {
}
