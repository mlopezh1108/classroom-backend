package net.developz.classroom.backend.catalog.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.developz.classroom.backend.catalog.application.usecases.SubjectUseCase;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.SubjectRepository;

@Configuration
public class CatalogConfig {

    @Bean
    public SubjectUseCase subjectUseCase(SubjectRepository subjectRepository) {
        return new SubjectUseCase(subjectRepository);
    }
}
