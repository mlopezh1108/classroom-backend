package net.developz.classroom.backend.shared.infrastructure.persistence.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaRepositoryAdapterTest {

    @Mock
    private JpaRepository<Object, String> repository;

    private TestAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new TestAdapter(repository);
    }

    @Test
    void shouldFindById() {
        Object entity = new Object();
        when(repository.findById("1")).thenReturn(Optional.of(entity));

        Optional<Object> result = adapter.findById("1");

        assertThat(result).isPresent().contains(entity);
        verify(repository).findById("1");
    }

    @Test
    void shouldSave() {
        Object entity = new Object();
        when(repository.save(entity)).thenReturn(entity);

        Object result = adapter.save(entity);

        assertThat(result).isEqualTo(entity);
        verify(repository).save(entity);
    }

    @Test
    void shouldDeleteById() {
        doNothing().when(repository).deleteById("1");

        adapter.deleteById("1");

        verify(repository).deleteById("1");
    }

    @Test
    void shouldFindAllPaginated() {
        PaginationCriteria criteria = PaginationCriteria.of(0, 10);
        Page<Object> page = new PageImpl<>(requireNonNull(List.of(new Object())));
        when(repository.findAll(requireNonNull(any(Pageable.class)))).thenReturn(page);

        PaginatedResult<Object> result = adapter.findAll(criteria);

        assertThat(result.items()).hasSize(1);
        assertThat(result.totalElements()).isEqualTo(1L);
        verify(repository).findAll(requireNonNull(any(Pageable.class)));
    }

    private static class TestAdapter
            extends JpaRepositoryAdapter<Object, Object, String, JpaRepository<Object, String>> {
        public TestAdapter(JpaRepository<Object, String> repository) {
            super(repository);
        }

        @Override
        protected Object toModel(Object entity) {
            return entity;
        }

        @Override
        protected Object toEntity(Object model) {
            return model;
        }
    }
}
