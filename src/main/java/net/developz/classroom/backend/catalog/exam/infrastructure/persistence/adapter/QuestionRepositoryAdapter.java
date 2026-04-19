package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.adapter;

import net.developz.classroom.backend.catalog.exam.application.port.QuestionRepositoryPort;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.Question;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.repository.QuestionRepository;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;

import org.springframework.stereotype.Component;

@Component
public class QuestionRepositoryAdapter extends JpaRepositoryAdapter<Question, String, QuestionRepository> implements QuestionRepositoryPort {

    public QuestionRepositoryAdapter(QuestionRepository repository) {
        super(repository);
    }
}
