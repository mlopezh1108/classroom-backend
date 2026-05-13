package net.developz.classroom.backend.shared.application.dto;

import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import java.util.List;

/**
 * Generic DTO for paginated responses.
 * Encapsulates the content list along with pagination metadata.
 *
 * @param <T> Type of the content items
 */
public record PageResponse<T>(
    List<T> content,
    int pageNumber,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean last
) {
    /**
     * Map a PaginatedResult to our custom PageResponse.
     * 
     * @param result PaginatedResult
     * @param <T> Content type
     * @return Standardized PageResponse
     */
    public static <T> PageResponse<T> from(PaginatedResult<T> result) {
        return new PageResponse<>(
            result.items(),
            result.currentPage(),
            result.size(),
            result.totalElements(),
            result.totalPages(),
            result.currentPage() == result.totalPages() - 1 || result.totalPages() == 0
        );
    }
}
