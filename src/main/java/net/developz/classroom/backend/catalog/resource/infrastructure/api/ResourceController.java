package net.developz.classroom.backend.catalog.resource.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.resource.application.dto.CreateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.dto.ResourceDTO;
import net.developz.classroom.backend.catalog.resource.application.dto.UpdateResourceRequest;
import net.developz.classroom.backend.catalog.resource.application.usecase.CreateResourceUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.DeleteResourceUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.ListSubjectResourcesUseCase;
import net.developz.classroom.backend.catalog.resource.application.usecase.UpdateResourceUseCase;
import net.developz.classroom.backend.catalog.resource.infrastructure.mapper.ResourceMapper;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return new ResponseEntity<>(mapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceDTO> updateResource(
            @PathVariable String resourceId,
            @RequestBody UpdateResourceRequest request) {
        Resource updated = updateResourceUseCase.execute(resourceId, request);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable String resourceId) {
        deleteResourceUseCase.execute(resourceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<ResourceDTO>> listSubjectResources(@PathVariable String subjectId) {
        List<Resource> resources = listSubjectResourcesUseCase.execute(subjectId);
        List<ResourceDTO> dtoList = resources.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
