package net.developz.classroom.backend.shared.domain.pagination;

import java.util.List;
import java.util.function.Function;

/**
 * Container for paginated results in DDD architecture.
 * Decouples the domain/application layer from Spring Data's Page.
 *
 * @param <T> Type of the data in the result
 */
public record PaginatedResult<T>(
        List<T> items,
        long totalElements,
        int totalPages,
        int currentPage,
        int size
) {
    /**
     * Maps the items in this result to a new type.
     *
     * @param mapper The function to apply to each item
     * @param <U>    The type of the new items
     * @return A new PaginatedResult with the mapped items
     */
    public <U> PaginatedResult<U> map(Function<? super T, U> mapper) {
        List<U> mappedItems = this.items.stream().map(mapper).toList();
        return new PaginatedResult<>(
                mappedItems,
                this.totalElements,
                this.totalPages,
                this.currentPage,
                this.size
        );
    }
}
