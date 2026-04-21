package net.developz.classroom.backend.shared.application.dto;

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
     * Map a Spring Data Page to our custom PageResponse.
     * 
     * @param page Spring Data Page
     * @param <T> Content type
     * @return Standardized PageResponse
     */
    public static <T> PageResponse<T> from(org.springframework.data.domain.Page<T> page) {
        return new PageResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
        );
    }
}
