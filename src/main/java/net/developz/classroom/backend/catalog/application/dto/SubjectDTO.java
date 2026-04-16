package net.developz.classroom.backend.catalog.application.dto;

import java.util.Map;

public record SubjectDTO(
        String id,
        String subjectCode,
        Integer level,
        Map<String, String> subjectNames
) {
}
