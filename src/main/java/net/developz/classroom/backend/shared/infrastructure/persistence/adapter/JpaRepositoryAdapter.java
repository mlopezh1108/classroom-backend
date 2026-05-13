package net.developz.classroom.backend.shared.infrastructure.persistence.adapter;

import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import static java.util.Objects.*;

import java.util.List;
import java.util.Optional;

/**
 * Generic infrastructure adapter that implements {@link RepositoryPort}
 * by delegating all CRUD operations to a Spring Data {@link JpaRepository}.
 * <p>
 * Subclasses only need to extend this adapter to add domain-specific methods
 * (e.g. {@code findByCode}, {@code existsByName}).
 *
 * @param <M>  Domain model type
 * @param <E>  Persistence entity type
 * @param <ID> Identifier type
 * @param <R>  Concrete JPA repository type
 */
public abstract class JpaRepositoryAdapter<M, E, ID, R extends JpaRepository<E, ID>>
        implements RepositoryPort<M, ID> {

    protected final R repository;

    protected JpaRepositoryAdapter(R repository) {
        this.repository = repository;
    }

    /**
     * Map a persistence entity to a domain model.
     *
     * @param entity Persistence entity
     * @return Domain model
     */
    protected abstract M toModel(E entity);

    /**
     * Map a domain model to a persistence entity.
     *
     * @param model Domain model
     * @return Persistence entity
     */
    protected abstract E toEntity(M model);

    @Override
    public List<M> findAll() {
        return repository.findAll().stream()
                .map(this::toModel)
                .toList();
    }

    @Override
    public PaginatedResult<M> findAll(PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<E> page = repository.findAll(pageable);

        return new PaginatedResult<>(
                page.getContent().stream().map(this::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize());
    }

    @Override
    public Optional<M> findById(ID id) {
        return repository.findById(requireNonNull(id)).map(this::toModel);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(requireNonNull(id));
    }

    @Override
    public M save(M model) {
        E entity = toEntity(model);
        E savedEntity = repository.save(requireNonNull(entity));
        return toModel(savedEntity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(requireNonNull(id));
    }
}
