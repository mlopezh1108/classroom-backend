package net.developz.classroom.backend.catalog.resource.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.usecase.*;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.domain.model.Resource;
import net.developz.classroom.backend.shared.application.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final CreateResourceUseCase createResourceUseCase;
    private final UpdateResourceUseCase updateResourceUseCase;
    private final DeleteResourceUseCase deleteResourceUseCase;
    private final ListSubjectResourcesUseCase listSubjectResourcesUseCase;
    private final ResourceMapper mapper;

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestBody CreateResourceRequest request) {
        Resource saved = createResourceUseCase.execute(request);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceDTO> updateResource(
            @PathVariable String resourceId,
            @RequestBody UpdateResourceRequest request) {
        Resource updated = updateResourceUseCase.execute(resourceId, request);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable String resourceId) {
        deleteResourceUseCase.execute(resourceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subject/{subjectId}")
    @PreAuthorize("hasAuthority('RESOURCE_VIEW')")
    public ResponseEntity<PageResponse<ResourceDTO>> listSubjectResources(
            @PathVariable String subjectId,
            Pageable pageable) {
        return ResponseEntity.ok(PageResponse.from(
                listSubjectResourcesUseCase.execute(subjectId, pageable).map(mapper::toDto)
        ));
    }
}
