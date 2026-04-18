package net.developz.classroom.backend.shared.application.annotation;

import java.lang.annotation.*;

/**
 * Marks a class as an application-layer use case.
 * This is a pure marker annotation with no framework dependencies.
 * Beans are registered via infrastructure configuration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCase {
}


