package net.developz.classroom.backend.shared.infrastructure.persistence.adapter;

import net.developz.classroom.backend.shared.application.port.RepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Generic infrastructure adapter that implements {@link RepositoryPort}
 * by delegating all CRUD operations to a Spring Data {@link JpaRepository}.
 * <p>
 * Subclasses only need to extend this adapter to add domain-specific methods
 * (e.g. {@code findByCode}, {@code existsByName}).
 *
 * @param <E>  Entity type
 * @param <ID> Identifier type
 * @param <R>  Concrete JPA repository type
 */
public abstract class JpaRepositoryAdapter<E, ID, R extends JpaRepository<E, ID>>
        implements RepositoryPort<E, ID> {

    protected final R repository;

    protected JpaRepositoryAdapter(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
