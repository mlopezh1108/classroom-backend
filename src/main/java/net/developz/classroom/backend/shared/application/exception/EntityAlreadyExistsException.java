package net.developz.classroom.backend.shared.application.exception;

import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {
    private final Class<?> useCaseClass;
    private final Class<?> entityClass;

    public EntityAlreadyExistsException(String message, Class<?> useCaseClass, Class<?> entityClass) {
        super(message);
        this.useCaseClass = useCaseClass;
        this.entityClass = entityClass;
    }
}
