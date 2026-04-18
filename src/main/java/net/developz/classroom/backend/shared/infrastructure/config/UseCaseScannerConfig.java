package net.developz.classroom.backend.shared.infrastructure.config;

import net.developz.classroom.backend.shared.application.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Infrastructure configuration to register classes annotated with {@link UseCase}
 * as Spring Beans.
 *
 * This allows the Application layer to remain completely decoupled from Spring
 * while still benefiting from Dependency Injection.
 */
@Configuration
@ComponentScan(
        basePackages = "net.developz.classroom.backend",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = UseCase.class
        ),
        useDefaultFilters = false
)
public class UseCaseScannerConfig {
}


