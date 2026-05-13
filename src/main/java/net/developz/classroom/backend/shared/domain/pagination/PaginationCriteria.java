package net.developz.classroom.backend.shared.domain.pagination;

/**
 * Criteria for paginated queries in DDD architecture.
 * Decouples the domain/application layer from Spring Data's Pageable.
 *
 * @param page Zero-based page index
 * @param size The size of the page to be returned
 */
public record PaginationCriteria(int page, int size) {
    public PaginationCriteria {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        }
    }

    public static PaginationCriteria of(int page, int size) {
        return new PaginationCriteria(page, size);
    }
}
