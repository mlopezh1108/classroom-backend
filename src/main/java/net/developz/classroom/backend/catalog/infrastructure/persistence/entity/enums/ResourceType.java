package net.developz.classroom.backend.catalog.infrastructure.persistence.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceType {
    PDF("application/pdf"),
    VIDEO("video/mp4"),
    IMAGE("image/jpeg"),
    DOCUMENT("application/msword"),
    LINK("text/html"),
    OTHER("");

    private final String mimeType;
}
