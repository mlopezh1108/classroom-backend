package net.developz.classroom.backend.catalog.resource.application.dto;

import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;

public record CreateResourceRequest(
    String title,
    ResourceType resourceType,
    String contentUrl,
    String subjectId
) {}
