package net.developz.classroom.backend.catalog.group.infrastructure.api;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.group.application.dto.CreateGroupRequest;
import net.developz.classroom.backend.catalog.group.application.dto.GroupResponse;
import net.developz.classroom.backend.catalog.group.application.dto.UpdateGroupRequest;
import net.developz.classroom.backend.catalog.group.application.usecase.*;
import net.developz.classroom.backend.catalog.group.infrastructure.mapper.GroupMapper;
import net.developz.classroom.backend.catalog.group.infrastructure.persistence.entity.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/catalog/groups")
@RequiredArgsConstructor
public class GroupController {

    private final FindAllGroupsUseCase findAllGroupsUseCase;
    private final FindGroupByIdUseCase findGroupByIdUseCase;
    private final CreateGroupUseCase createGroupUseCase;
    private final UpdateGroupUseCase updateGroupUseCase;
    private final DeleteGroupUseCase deleteGroupUseCase;
    private final GroupMapper groupMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('GROUP_VIEW')")
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        List<GroupResponse> groups = findAllGroupsUseCase.execute().stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GROUP_VIEW')")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable String id) {
        Group group = findGroupByIdUseCase.execute(id);
        return ResponseEntity.ok(groupMapper.toDto(group));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('GROUP_CREATE')")
    public ResponseEntity<GroupResponse> createGroup(@RequestBody CreateGroupRequest request) {
        Group group = groupMapper.toEntity(request);
        Group savedGroup = createGroupUseCase.execute(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupMapper.toDto(savedGroup));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('GROUP_UPDATE')")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable String id, @RequestBody UpdateGroupRequest request) {
        Group existingGroup = findGroupByIdUseCase.execute(id);
        groupMapper.updateEntityFromRequest(request, existingGroup);
        Group updatedGroup = updateGroupUseCase.execute(existingGroup);
        return ResponseEntity.ok(groupMapper.toDto(updatedGroup));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GROUP_DELETE')")
    public ResponseEntity<Void> deleteGroup(@PathVariable String id) {
        deleteGroupUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
