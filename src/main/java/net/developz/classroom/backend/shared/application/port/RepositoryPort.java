package net.developz.classroom.backend.shared.application.port;

import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import java.util.List;
import java.util.Optional;

/**
 * Generic output port for repositories in DDD architecture.
 * <p>
 * Defines the base CRUD contract expected by the application layer,
 * without coupling to any persistence framework.
 *
 * @param <M>  Domain model type
 * @param <ID> Model identifier type
 */
public interface RepositoryPort<M, ID> {

    List<M> findAll();

    PaginatedResult<M> findAll(PaginationCriteria criteria);

    Optional<M> findById(ID id);

    boolean existsById(ID id);

    M save(M model);

    void deleteById(ID id);
}
