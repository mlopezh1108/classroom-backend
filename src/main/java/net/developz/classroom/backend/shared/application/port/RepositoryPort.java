package net.developz.classroom.backend.shared.application.port;

import java.util.List;
import java.util.Optional;

/**
 * Generic output port for repositories in DDD architecture.
 * <p>
 * Defines the base CRUD contract expected by the application layer,
 * without coupling to any persistence framework.
 *
 * @param <E>  Domain entity type
 * @param <ID> Entity identifier type
 */
public interface RepositoryPort<E, ID> {

    List<E> findAll();

    Optional<E> findById(ID id);

    boolean existsById(ID id);

    E save(E entity);

    void deleteById(ID id);
}
